package br.fai.lds.db.dao.impl;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.AnnouncementDao;
import br.fai.lds.model.entities.AnnouncementModel;
import br.fai.lds.model.enums.ServiceType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnnouncementDaoImpl implements AnnouncementDao<AnnouncementModel> {
    @Override
    public List<AnnouncementModel> find() {
        List<AnnouncementModel> items = new ArrayList<>();

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM anuncio";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnnouncementModel address = new AnnouncementModel();
                address.setId(resultSet.getInt("id"));
                address.setTitulo(resultSet.getString("titulo"));
                address.setDescricao(resultSet.getString("descricao"));
                String serviceType = resultSet.getString("tipo");
                address.setTipo(ServiceType.valueOf(serviceType));
                address.setPreco(resultSet.getString("preco"));
                items.add(address);
            }


        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return items;
    }

    @Override
    public AnnouncementModel findById(int id) {
        AnnouncementModel item = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "SELECT * FROM anuncio where id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new AnnouncementModel();
                item.setId(resultSet.getInt("id"));
                item.setTitulo(resultSet.getString("titulo"));
                item.setDescricao(resultSet.getString("descricao"));
                String serviceType = resultSet.getString("tipo");
                item.setTipo(ServiceType.valueOf(serviceType));
                item.setPreco(resultSet.getString("preco"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection, resultSet);
        }

        return item;
    }


    @Override
    public int create(AnnouncementModel entity) {
        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int id = -1;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "INSERT INTO public.anuncio(id, titulo, descricao, tipo, preco, categoria_id, tipo_servico_id, usuario_id)  VALUES (default, ?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getTitulo());
            preparedStatement.setString(2, entity.getDescricao());
            String serviceType = entity.getTipo().toString();
            preparedStatement.setString(3, serviceType);
            preparedStatement.setString(4,entity.getPreco());


            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

            connection.commit();
            return id;


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return id;
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection,resultSet);
        }
    }



    @Override
    public boolean update(AnnouncementModel entity) {

        Connection connection = null;

        PreparedStatement preparedStatement = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        String sql = "UPDATE public.endereco SET id=?, logradouro=?, numero=?, cep=?, bairro=?, usuario_id=?, municipio_id=?;";

        try {
            connection = ConnectionFactory.getConnection();

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getDescricao());
            preparedStatement.setString(3, entity.getTitulo());
            preparedStatement.setInt(4, entity.getId());



            preparedStatement.execute();
            connection.commit();
            return true;


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
        } finally {
            ConnectionFactory.close(preparedStatement, connection);
        }
    }

    @Override
    public boolean deleteById(int id) {
        boolean result = false;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

//        tudo relacionado a linguegem SQL com a linguagem em si colocar letra maiuscusla
        final String sql = "DELETE FROM public.endereco WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            connection.setAutoCommit(false);

            preparedStatement.execute();

            connection.commit();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
//            motivo do finally no try cath camando para o try cath ctrl + alt + t
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            ConnectionFactory.close(preparedStatement,connection);
        }

        return result;
    }
}
