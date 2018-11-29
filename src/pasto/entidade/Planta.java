package pasto.entidade;
/**/
import java.awt.Point;

import javax.swing.ImageIcon;

import pasto.Pasto;

public class Planta extends SerVivo implements Entidade {

	/** O �cone desta entidade. */
    private final ImageIcon imagem = new ImageIcon("imagens/plant.gif"); 
    private static final int REPRODUCAO = 50;
	
	public Planta(Pasto pasto) {
		super(pasto);
		tempoParaNovaReproducao = REPRODUCAO;
	}

	@Override
	public void reproduzir() {
		Point vizinho = 
                (Point)getMembroAleatorio
                (pasto.getVizinhosLivres(this));
		
		pasto.adicionaEntidade(new Planta(pasto), vizinho);
	}

	@Override
	public void tick() {
		tempoParaNovaReproducao--;
        
        if(tempoParaNovaReproducao == 0) {      
            reproduzir();

            tempoParaNovaReproducao = REPRODUCAO;
        }
		
	}

	@Override
	public ImageIcon getImagem() {
		return imagem;
	}

	@Override
	public boolean eCompativel(Entidade outraEntidade) {
		// plantas sempre s�o compat�veis com outras entidades
		return !(outraEntidade instanceof Cerca);
	}

}
