package br.mod.escolar.relatorios.composers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import br.mod.escolar.model.util.RealPathApp;
import br.mod.escolar.model.util.ReportFrequencia;
import br.mod.escolar.relatorios.beans.FrequenciaReportDataBean;



public class FrequenciaBeanDataSourceComposer implements ComposerIF<ReportFrequencia, FrequenciaReportDataBean> {

	private ArrayList<FrequenciaReportDataBean> beans;

	public FrequenciaBeanDataSourceComposer() {
		this.beans = new ArrayList<FrequenciaReportDataBean>();
	}

	@Override
	public void addData(ReportFrequencia object) {
		createNewDataBean(object);

	}

	private void createNewDataBean(ReportFrequencia student) {
		beans.add(createNewDataBeanS(student));
	}

	@Override
	public void addData(List<ReportFrequencia> objects) {
		for (ReportFrequencia s : objects) {
			createNewDataBean(s);
		}

	}

	@Override
	public ArrayList<FrequenciaReportDataBean> getBeansList() {
		
		return beans;
	}

	@Override
	public Object[] getBeansAsArray() {
		
		return beans.toArray();
	}

	private FrequenciaReportDataBean createNewDataBeanS(ReportFrequencia frequencia) {

		FrequenciaReportDataBean dataBean = new FrequenciaReportDataBean();

		if (frequencia.getNomeAluno() != null) {
			dataBean.setNomeAluno(frequencia.getNomeAluno());
		}
		
		if(frequencia.getFaltas() !=null){
			dataBean.setFaltas(frequencia.getFaltas());
			
		}

        if(frequencia.getPercentagem() != null){
			dataBean.setPercentagem(frequencia.getPercentagem());
		
        }
        
        if(frequencia.getDisciplina() != null){
			dataBean.setDisciplina(frequencia.getDisciplina());
		
        }
        
        if(frequencia.getGrau() != null){
			dataBean.setGrau(frequencia.getGrau());
		
        }
        
        if(frequencia.getSerie() != null){
			dataBean.setSerie(frequencia.getSerie());
		
        }
        
        if(frequencia.getProfessor() != null){
			dataBean.setProfessor(frequencia.getProfessor());
		
        }
        
        if(frequencia.getTurma() != null){
			dataBean.setTurma(frequencia.getTurma());
		
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

}
