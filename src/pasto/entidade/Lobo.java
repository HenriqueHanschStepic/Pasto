package pasto.entidade;
import javax.swing.*;

import java.awt.Point;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;

import pasto.Pasto;
import pasto.gui.PastoGUI;

public class Lobo extends Animal implements Entidade {
	/** O ícone desta entidade. */
    private final ImageIcon imagem = new ImageIcon("imagens/wolf.gif"); 
    private static final int REPRODUCAO = 100;
    /** A posição desta entidade. */
    /** O número de tiques que devem passar até que essa entidade possa se mover. */
    protected int tempoParaMover;
    int tempoParaReproduzir = 100;
    protected boolean vivo = true;

    /**
     * Cria uma nova instância desta classe, com o pasto dado como seu pasto
     * @param pasto o pasto a qual esta entidade deve pertencer.
     */

    /**
     * Cria uma nova instância desta classe, com o pasto como seu pasto
 	 * e uma flag "vivo" para indicar se é um Dummy móvel ou estático.
     * @param pasto o pasto a qual esta entidade pertence.
     * @param vivo true ou false se o Dummy é vivo ou não.
     */
    public Lobo(Pasto pasto) {
    	super(pasto);
        tempoParaMover      = 10;
        tempoParaNovaReproducao = REPRODUCAO;
    }
    
    /**
     * Faz as ações relevantes a esta entidade, dependendo de que tipo de entidade seja.
     */
    @Override
    public void tick() {
    	tempoParaNovaReproducao--; 
    	tempoParaMover--;
        if(tempoParaMover == 0) {
            Point neighbour = 
                (Point)getMembroAleatorio
                (pasto.getVizinhosLivres(this));
            
            if(neighbour != null) 
                pasto.moveEntidade(this, neighbour);

            tempoParaMover = 10;
        }
        
        if(tempoParaNovaReproducao == 0) {      
            reproduzir();

            tempoParaNovaReproducao = REPRODUCAO;
        }
    }
    
    /** 
     * Retorna o ícone desta entidade, para ser mostrada pela gui do pasto
     * @see PastoGUI
     */
    @Override
    public ImageIcon getImagem() { return imagem; }

    /**
     * Testa se esta entidade pode estar na mesma posição no pasto da outra, passada por parâmetro.
     */
    @Override
    public boolean eCompativel(Entidade otherEntity) { return false; }
    
    protected static <X> X getMembroAleatorio(Collection<X> c) {
        if (c.size() == 0)
            return null;
        
        Iterator<X> it = c.iterator();
        int n = (int)(Math.random() * c.size());

        while (n-- > 0) {
            it.next();
        }

        return it.next();
    }

	@Override
	public void reproduzir() {
		// TODO Auto-generated method stub
		Point vizinho = 
                (Point)getMembroAleatorio
                (pasto.getVizinhosLivres(this));
		
		pasto.adicionaEntidade(new Lobo(pasto), vizinho);
	}
	}

