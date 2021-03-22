package br.dev.diego.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.dev.diego.livraria.dao.DAO;
import br.dev.diego.livraria.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	private Integer autorId;

	public Autor getAutor() {
		return autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}

	public void carregar(Autor autor) {
		System.out.println("Carregando autor");
		this.autor = autor;
	}

	public void carregarAutorPeloId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public void remover(Autor autor) {
		System.out.println("Removendo autor");
		try {
			new DAO<Autor>(Autor.class).remove(autor);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Autor não pode ser removido."));
		}

	}

}
