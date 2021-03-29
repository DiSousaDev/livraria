package br.dev.diego.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.dev.diego.livraria.dao.DAO;
import br.dev.diego.livraria.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();

	private Integer usuarioId;

	public Usuario getUsuario() {
		return usuario;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public List<Usuario> getUsuarios() {
		return new DAO<Usuario>(Usuario.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando usuario " + this.usuario.getNome() + " Id: " + this.usuario.getId());

		if (this.usuario.getId() == null) {
			System.out.println("adiciona");
			new DAO<Usuario>(Usuario.class).adiciona(this.usuario);
		} else {
			System.out.println("atualiza");
			new DAO<Usuario>(Usuario.class).atualiza(this.usuario);
		}

		this.usuario = new Usuario();

		return "usuario?faces-redirect=true";
	}

	public void carregar(Usuario usuario) {
		System.out.println("Carregando usuario id: " + usuario.getId());
		this.usuario = usuario;
	}

	public void carregarUsuarioPeloId() {
		this.usuario = new DAO<Usuario>(Usuario.class).buscaPorId(usuarioId);
	}

	public void remover(Usuario usuario) {
		System.out.println("Removendo usuario");
		try {
			new DAO<Usuario>(Usuario.class).remove(usuario);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("usuario", new FacesMessage("Usuario não pode ser removido."));
		}

	}

}
