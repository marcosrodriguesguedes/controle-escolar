package br.mod.escolar.control;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import br.mod.escolar.model.entities.AvaliacaoConceitual;
import br.mod.escolar.model.entities.Competencia;
import br.mod.escolar.model.entities.Habilidade;

public class AvaliacaoConceitualManager {

	private static AvaliacaoConceitualManager manager = null;

	private AvaliacaoConceitual avaliacao = null;

	private AvaliacaoConceitualManager() {

		avaliacao = new AvaliacaoConceitual();
		try {
			loadCompetenciasHabilidades();
		} catch (JDOMException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

	public static synchronized AvaliacaoConceitualManager getInstance()  {

		if (manager == null) {

			manager = new AvaliacaoConceitualManager();
		}
		return manager;
	}

	private void loadCompetenciasHabilidades() throws JDOMException, IOException {

		if (null != avaliacao) {

			File f = new File("src/habilidades.xml");

			SAXBuilder sb = new SAXBuilder();

			Document d = sb.build(f);

			Element mural = d.getRootElement();

			List<?> elements = mural.getChildren();
			Iterator<?> i = elements.iterator();

			while (i.hasNext()) {

				Element element = (Element) i.next();

				Competencia c = new Competencia();

				c.setCode(element.getAttributeValue("code"));
				c.setDescription(element.getAttributeValue("description"));
				c.setId(Integer.parseInt(element.getAttributeValue("number")));

				this.avaliacao.getCompetencias().add(c);

				List<?> habilidades = element.getChildren();
				Iterator<?> o = habilidades.iterator();

				while (o.hasNext()) {

					Element element2 = (Element) o.next();

					Habilidade h = new Habilidade();

					h.setCode(element2.getAttributeValue("code"));
					h.setDescription(element2.getAttributeValue("description"));
					h.setId(Integer.parseInt(element.getAttributeValue("number")));
					
					this.avaliacao.getHabilidades().add(h);

				}

			}

		}

	}

	public AvaliacaoConceitual getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoConceitual avaliacao) {
		this.avaliacao = avaliacao;
	}
	

	
}
