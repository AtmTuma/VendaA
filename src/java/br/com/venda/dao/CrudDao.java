
package br.com.venda.dao;

import br.com.venda.util.exception.ErroSistema;
import java.util.List;

/**
 *
 * @author alex
 * @param <E>
 */
public interface CrudDao<E> {
    
    public void salvar(E entidade) throws ErroSistema;
    public void deletar(E entidade) throws ErroSistema;
    public List<E> buscar() throws ErroSistema;
}
