package br.fai.lds.client.service;

public interface PublicationService <T> extends BaseService<T> {

    PublicationService validateTituloDescricao(String Titulo, String descricao);
}
