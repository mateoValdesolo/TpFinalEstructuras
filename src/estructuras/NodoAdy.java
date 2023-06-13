package estructuras;

/**
 * @author Mateo Valdesolo
 */
public class NodoAdy {

    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private Object etiqueta;

    public NodoAdy(NodoVert vertice, NodoAdy sigAdyacente, Object etiqueta){
        this.vertice = vertice;
        this.sigAdyacente = sigAdyacente;
        this.etiqueta = etiqueta;
    }

    public NodoVert getVertice(){
        return vertice;
    }

    public NodoAdy getSigAdyacente(){
        return sigAdyacente;
    }

    public Object getEtiqueta(){
        return etiqueta;
    }

    public void setVertice(NodoVert vertice){
        this.vertice = vertice;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente){
        this.sigAdyacente = sigAdyacente;
    }

    public void setEtiqueta(Object etiqueta){
        this.etiqueta = etiqueta;
    }
}
