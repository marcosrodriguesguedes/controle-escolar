package br.mod.escolar.relatorios.composers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.util.EquipeReport;
import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.relatorios.beans.EquipesReportDataBean;

public class EquipesBeanDataSourceComposer implements ComposerIF<EquipeReport, EquipesReportDataBean> {

	private ArrayList<EquipesReportDataBean> beans;
	
	
	public EquipesBeanDataSourceComposer(){
		this.beans = new ArrayList<EquipesReportDataBean>();
	}
	
	@Override
	public void addData(EquipeReport object) {
		createNewDataBean(object);
		
	}
	
	private void createNewDataBean(EquipeReport object) {
		beans.add(createNewDataBeanS(object));
	}

	private EquipesReportDataBean createNewDataBeanS(EquipeReport equipe) {
		
		EquipesReportDataBean dataBean = new EquipesReportDataBean();
		
		
		if(equipe.getDescricao() != null){
			dataBean.setDescricao(equipe.getDescricao());
		}
		
		if(equipe.getBimestre() != null){
			dataBean.setBimestre(equipe.getBimestre());
		}
		
		if(null != equipe.getAlunos()){
			dataBean.setAlunos(equipe.getAlunos());
		}
		
		
		String initialPath = RealPathApp.getPathApp();
		File bannerLogoPath = new File(initialPath
				+ "resources/img/marca_transparente.png");
		
		try {
			dataBean.setBANNER_LOGO(ImageIO.read(bannerLogoPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataBean;
		
	}

	@Override
	public void addData(List<EquipeReport> objects) {
		for (EquipeReport s : objects) {
			createNewDataBean(s);
		}
		
	}

	@Override
	public ArrayList<EquipesReportDataBean> getBeansList() {
		
		return beans;
	}

	@Override
	public Object[] getBeansAsArray() {
		
		return beans.toArray();
	}

}
