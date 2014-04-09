import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class SyncService implements Runnable{
	
	public static void main(String args[]){

		new SyncService();

	}

	public SyncService (){

		new Thread(this).start();

	}	

	public void run(){		
		
		long time = System.currentTimeMillis();  

		CloseableHttpResponse response;

		HttpEntity entity;

		/** objeto que contém o conteudo do arquivo .zip*/
		ZipInputStream zipInputStream; 

		/** representa cada arquivo dentro do .zip*/
		ZipEntry zipEntry;		

		/** criamos um Http client e preparamos uma requisição para Constants.SYNC_URL*/
		CloseableHttpClient httpclient = HttpClients.createDefault();		
		HttpGet httpGet = new HttpGet(Constants.SYNC_URL);
		/** criamos um Http client e preparamos uma requisição para Constants.SYNC_URL*/

		try {		

			/** executa http get no servidor definido em Constants.SYNC_URL*/
			response = httpclient.execute(httpGet);
			
			System.out.println("Tempo até o download = " + (System.currentTimeMillis()-time)/1000.0 + " segundos");  

			if(response.getStatusLine().getStatusCode() == Constants.STATUS_OK){

				System.out.println(response.getStatusLine());			

				/** obtemos o inputstream de resposta do servidor e abrimos o .zip*/
				entity = response.getEntity();
				zipInputStream = new ZipInputStream(entity.getContent());			

				/** aqui lemos todos os arquivos que estão dentro do .zip*/
				while ((zipEntry = zipInputStream.getNextEntry()) != null) {

					/** só nos interessa o arquivo com extensão htm*/
					if(zipEntry.getName().contains("HTM")){

						/** criamos o arquivo .htm local*/
						FileOutputStream fileoutputstream = new FileOutputStream(Constants.SYNC_FILE_NAME); 					

						/** Transferimos o conteúdo do arquivo .htm que está dentro do zip para um arquivo local fora do zip */
						for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
							fileoutputstream.write(c);						
						} 					
						/** Transferimos o conteúdo do arquivo .htm que está dentro do zip para um arquivo local fora do zip */

						/** Fechamos o arquivo zip e o output  */
						zipInputStream.close();
						fileoutputstream.close();
						/** Fechamos o arquivo zip e o output  */

						break;
					}
				}
			}
			else{

				/**erro na requisição do HTTP Get ao servidor*/				
				System.out.println(response.getStatusLine());	
			}

		} catch (MalformedURLException e) {				
			e.printStackTrace();				
		} catch (IOException e) {				
			e.printStackTrace();				
		} 
		/** aqui lemos todos os arquivos que estão dentro do .zip*/

		/** Parser */
		
		System.out.println("Tempo até salvar o arquivo local = " + (System.currentTimeMillis()-time)/1000.0 + " segundos"); 

		/** passamos para syncSorteios, o número do jogo do qual devemos começar a parser. Em caso de FullSync 1, em caso de 
		 * somente buscar por um jogo novo, passar o último jogo salvo, ex 1500
		 */
		List<Sorteio> listaSorteios = syncSorteios(Constants.SYNC_GAME_NUMBER);
		
		System.out.println("Tempo até termino do parser = " + (System.currentTimeMillis()-time)/1000.0 + " segundos"); 
		
		printSorteios(listaSorteios);

	}  

	private List<Sorteio> syncSorteios(Integer numeroSorteio){

		List<Sorteio> listaSorteios = new ArrayList<Sorteio>();

		if(numeroSorteio >= 1){

			/** carrega o arquivo dentro de um stringbuilder*/
			StringBuilder builder = Utils.fileToLines(Constants.SYNC_FILE_NAME);

			/** transforma o arquivo em apenas um string*/
			String arquivo = builder.toString();

			/** retira todas as informações que estão antes do primeiro jogo*/
			arquivo = arquivo.substring(arquivo.indexOf("<td>"+numeroSorteio+"</td>"));

			while(!arquivo.equalsIgnoreCase("</table></body></html>")){

				String linhaSorteio = arquivo.substring(arquivo.indexOf("<td>"), arquivo.indexOf("</tr>"));

				arquivo = arquivo.substring(arquivo.indexOf("</tr>")+5);				

				linhaSorteio = linhaSorteio.replaceAll("</td>", "<td>");

				StringTokenizer tokens = new StringTokenizer(linhaSorteio, "<td>");

				String [] valores = new String[tokens.countTokens()];

				for(int i = 0; i< valores.length; i++){
					valores[i] = tokens.nextToken();
				}	

				Sorteio sorteio = new Sorteio();

				sorteio.setNumeroSorteio(Integer.valueOf(valores[0]));
				sorteio.setDataSorteio(Utils.stringToDate(valores[1]));
				sorteio.setDezenaUm(Integer.valueOf(valores[2]));
				sorteio.setDezenaDois(Integer.valueOf(valores[3]));
				sorteio.setDezenaTres(Integer.valueOf(valores[4]));
				sorteio.setDezenaQuatro(Integer.valueOf(valores[5]));
				sorteio.setDezenaCinco(Integer.valueOf(valores[6]));
				sorteio.setDezenaSeis(Integer.valueOf(valores[7]));
				sorteio.setArrecadacaoTotal(Utils.formataStringToDouble(valores[8]));
				sorteio.setGanhadoresMega(Integer.valueOf(valores[9]));
				sorteio.setRateioMega(Utils.formataStringToDouble(valores[10]));
				sorteio.setGanhadoresQuina(Integer.valueOf(valores[11]));
				sorteio.setRateioQuina(Utils.formataStringToDouble(valores[12]));
				sorteio.setGanhadoresQuadra(Integer.valueOf(valores[13]));
				sorteio.setRateioQuadra(Utils.formataStringToDouble(valores[14]));
				sorteio.setAcumulado(valores[15].equalsIgnoreCase("sim") ? Boolean.TRUE : Boolean.FALSE);
				sorteio.setValorAcumulado(Utils.formataStringToDouble(valores[16]));					
				sorteio.setEstimativaPremio(Utils.formataStringToDouble(valores[17]));
				sorteio.setAcumuladoMegaVirada(Utils.formataStringToDouble(valores[18]));

				listaSorteios.add(sorteio);
			}
		}

		return listaSorteios;
	}
	
	private void printSorteios(List<Sorteio> sorteios){
			
		for(Sorteio sorteio : sorteios){
			System.out.println(sorteio);
		}
		
	}
}
