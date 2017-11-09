
package br.com.venda.bean;

import br.com.venda.dao.CrudDao;
import br.com.venda.util.exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author alex
 * @param <E>
 * @param <D>
 */
public abstract class CrudBean<E, D extends CrudDao> {
    
    private String estadoTela = "buscar";//inserir, buscar, editar
    private E entidade;
    private List<E> entidades;
    
    public void novo(){
        entidade = criaNovaEntidade();
        mudarParaInserir();
    }
    
    public void salvar(){
        try {
            getDAO().salvar(entidade);
            entidade = criaNovaEntidade();
            adicionarMensagem("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            mudarParaBuscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void editar(E entidade){
        this.entidade = entidade;
        mudarParaEditar();
    }
    public void deletar(E entidade){
        try {
            getDAO().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    public void buscar(){
        if(!isBuscar()){
            mudarParaBuscar();
            return;
        }
        try {
            entidades = getDAO().buscar();
            if(entidades == null || entidades.size() < 1){
                adicionarMensagem("Não tem nada cadastrado!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro){
        FacesMessage ms = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, ms);
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    public abstract D getDAO();
    public abstract E criaNovaEntidade();
    
    
    public Boolean isInserir(){
        return "inserir".equals(estadoTela);
    }
    public Boolean isBuscar(){
        return "buscar".equals(estadoTela);
    }
    public Boolean isEditar(){
        return "editar".equals(estadoTela);
    }
    public void mudarParaInserir(){
        estadoTela = "inserir";
    }
    public void mudarParaBuscar(){
        estadoTela = "buscar";
    }
    public void mudarParaEditar(){
        estadoTela = "editar";
    }
}
