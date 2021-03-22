package br.dev.diego.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.dev.diego.livraria.dao.DAO;
import br.dev.diego.livraria.modelo.Cliente;

@ManagedBean
@ViewScoped
public class ClienteBean {

	private Cliente cliente = new Cliente();

	public Cliente getCliente() {
		return cliente;
	}

	public List<Cliente> getClientes() {
		return new DAO<Cliente>(Cliente.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando cliente " + this.cliente.getNome() + " Id: " + this.cliente.getId());

		if (this.cliente.getId() == null) {
			System.out.println("adiciona");
			new DAO<Cliente>(Cliente.class).adiciona(this.cliente);
		} else {
			System.out.println("atualiza");
			new DAO<Cliente>(Cliente.class).atualiza(this.cliente);
		}

		this.cliente = new Cliente();

		return "cliente?faces-redirect=true";
	}

	public void carregar(Cliente cliente) {
		System.out.println("Carregando cliente id: " + cliente.getId());
		this.cliente = cliente;
	}

	public void remover(Cliente cliente) {
		System.out.println("Removendo cliente");
		try {
			new DAO<Cliente>(Cliente.class).remove(cliente);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("cliente", new FacesMessage("Cliente não pode ser removido."));
		}

	}

}
