package io.apihub.client.api;

import io.apihub.client.ApiClient;
import io.apihub.client.ApiException;
import io.apihub.client.model.Consultas;
import io.apihub.client.model.Creditos;
import io.apihub.client.model.Domicilios;
import io.apihub.client.model.Empleos;
import io.apihub.client.model.PersonaPeticion;
import io.apihub.client.model.PersonasPLD;
import io.apihub.client.model.Respuesta;
import io.apihub.client.model.Scores;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RCCLightFicoScorePldApiTest {

	private final RCCLightFicodcorePldApi api = new RCCLightFicodcorePldApi();
	private ApiClient apiClient;
	private String xApiKey = null;
	private String folioConsulta = null;

	@Before()
	public void setUp() {
		
		this.apiClient = api.getApiClient();
		this.apiClient.setBasePath("the_url");
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
		apiClient.setHttpClient(okHttpClient);
		this.xApiKey = "your_api_key";
	}

	@Test
	public void getFullReportTest() throws ApiException {
		Boolean xFullReport = true;
		PersonaPeticion body = new PersonaPeticion();
		body.setPrimerNombre("XXXXX");
		body.setSegundoNombre("XXXXX");
		body.setApellidoPaterno("XXXXX");
		body.setApellidoMaterno("XXXXX");
		body.setApellidoAdicional(null);
		body.setFechaNacimiento("yyyy-MM-dd");

		Respuesta response = api.getReporte(this.xApiKey, body, xFullReport);
		Assert.assertTrue(response.getFolioConsulta() != null);
	}

	@Test
	public void getSegmentedReportTest() throws ApiException {
		Boolean xFullReport = false;
		PersonaPeticion body = new PersonaPeticion();
		body.setPrimerNombre("XXXXX");
		body.setSegundoNombre("XXXXX");
		body.setApellidoPaterno("XXXXX");
		body.setApellidoMaterno("XXXXX");
		body.setApellidoAdicional(null);
		body.setFechaNacimiento("yyyy-MM-dd");

		Respuesta response = api.getReporte(this.xApiKey, body, xFullReport);
		Assert.assertTrue(response.getFolioConsulta() != null);
		
		if(response.getFolioConsulta()!=null) {
        	this.folioConsulta = response.getFolioConsulta();
        	
        	Creditos creditos = api.getCreditos(this.folioConsulta, this.xApiKey);
        	Assert.assertTrue(creditos.getCreditos() != null);
        	
        	Domicilios domicilios = api.getDomicilios(folioConsulta, this.xApiKey);
        	Assert.assertTrue(domicilios.getDomicilios() != null);
        	
        	Empleos empleos = api.getEmpleos(folioConsulta, this.xApiKey);
        	Assert.assertTrue(empleos.getEmpleos() != null);
        	
        	Consultas consultas = api.getConsultas(folioConsulta, this.xApiKey);
        	Assert.assertTrue(consultas.getConsultas() != null);
        	
        	Scores scores = api.getScores(folioConsulta, this.xApiKey);
        	Assert.assertTrue(scores.getScores() != null);
        	
        	PersonasPLD pld = api.getPLD(folioConsulta, this.xApiKey);
        	Assert.assertTrue(pld.getPld() != null);
        } 
	}
}
