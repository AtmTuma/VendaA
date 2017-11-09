
package br.com.venda.util;

import br.com.venda.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alex
 */
public class FabricaConexao {
    
    private static Connection conexao;
    private static final String URL_CONEXAO = "jdbc:postgresql://localhost:5432/Venda";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection getConexao() throws ErroSistema{
        if(conexao == null){
            try {
                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
                System.out.println("Conexão realizada com sucesso.");
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possivel conectar no banco de dados", ex);
            } catch (ClassNotFoundException ex) {
                throw  new ErroSistema("O driver do banco de dados não foi encontrado", ex);
            }
        }
        return conexao;
    }
    
    public static void fecharconexao() throws ErroSistema{
        if(conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Não foi possivel fechar o banco de dados", ex);
            }
        }
    }
    
}
