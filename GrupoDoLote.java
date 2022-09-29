import java.util.ArrayList;

/* Classe responsável pela organização dos lotes em diferentes cores */

public class GrupoDoLote {
    
    private ArrayList<Lote> lotes = new ArrayList<Lote>();
    
	/**
	 * Adiciona o lote ao Grupo
	 * @param lote pertencente ao grupo
	 */
    public void adicionarLote(Lote lote) {
		lotes.add(lote);
	}
	/**
	 * @return ArrayList de Lotes pertencentes ao Grupo
	 */
	public ArrayList<Lote> membrosDoGrupo() {
		return lotes;
	}
        
	/**
	 * @return Quantidade de lotes no Grupo
	 */
	public int tamanho() {
		return lotes.size();
	}
}