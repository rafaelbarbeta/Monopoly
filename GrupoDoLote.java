import java.util.ArrayList;

/* Classe responsável pela organização dos lotes em diferentes cores */

public class GrupoDoLote {
    
    private ArrayList<Lote> lotes = new ArrayList<Lote>();
    
    public void adicionarLote(Lote lote) {
		lotes.add(lote);
	}
	
	public ArrayList<Lote> membrosDoGrupo() {
		return lotes;
	}
        
	public int tamanho() {
		return lotes.size();
	}
}