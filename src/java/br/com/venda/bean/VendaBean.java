
package br.com.venda.bean;

import br.com.venda.dao.VendaDAO;
import br.com.venda.entity.Venda;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author alex
 * Esta classe é um managed bean é um gerenciador bean, ele vai intermediar o nosso
 * html para que consiga pegar as informações do xhtml para a classe java.
 */
@ManagedBean(name = "vendaBean")
@SessionScoped
public class VendaBean extends CrudBean<Venda, VendaDAO>{

    private VendaDAO vendaDAO;
    
    @Override
    public VendaDAO getDAO() {
        if(vendaDAO == null){
            vendaDAO = new VendaDAO();
        }
        return vendaDAO;
    }

    @Override
    public Venda criaNovaEntidade() {
        return new Venda();
    }
    
    
}
