package br.com.venda.dao;

import br.com.venda.entity.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.venda.util.FabricaConexao;
import br.com.venda.util.exception.ErroSistema;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alex
 */
public class VendaDAO implements CrudDao<Venda>{
    
    @Override
    public void salvar(Venda venda) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(venda.getOid() == null){
                ps = conexao.prepareStatement("INSERT INTO public.lancamento(dt_inicial, dt_final, vl_total, observacao) VALUES (?, ?, ?, ?);");
            } else{
                ps = conexao.prepareStatement("UPDATE public.lancamento SET dt_inicial=?, dt_final=?, vl_total=?, observacao=? WHERE oid = ?;");
                ps.setInt(5, venda.getOid());
            }
            ps.setDate(1, new Date(venda.getDt_inicial().getTime()));
            ps.setDate(2, new Date(venda.getDt_final().getTime()));
            ps.setDouble(3, venda.getVl_total());
            ps.setString(4, venda.getObservacao());
            ps.executeUpdate();
            FabricaConexao.fecharconexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar lancamento", ex);
        }
    }
    
    @Override
    public void deletar(Venda venda) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("DELETE from public.lancamento WHERE oid = ?");
            ps.setInt(1, venda.getOid());
            ps.execute();
            FabricaConexao.fecharconexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar lancamento", ex);
        }
    }
    
    @Override
    public List<Venda> buscar() throws ErroSistema{
        try {
           Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("Select * from public.lancamento;"); 
            ResultSet resultSet = ps.executeQuery();
            List<Venda> vendas = new ArrayList<>();
            while(resultSet.next()){
                Venda venda = new Venda();
                venda.setOid(resultSet.getInt("oid"));
                venda.setDt_inicial(resultSet.getDate("dt_inicial"));
                venda.setDt_final(resultSet.getDate("dt_final"));
                venda.setVl_total(resultSet.getDouble("vl_total"));
                venda.setObservacao(resultSet.getString("observacao"));
                vendas.add(venda);
            }
            FabricaConexao.fecharconexao();
            return vendas;
        } catch (SQLException e) {
            throw new ErroSistema("Erro ao buscar os lancamentos", e);
        }
        
    }
}
