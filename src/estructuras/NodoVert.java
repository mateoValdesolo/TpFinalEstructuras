package estructuras;

/**
 * @author Mateo Valdesolo
 */
public class NodoVert {

    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object elem, NodoVert sigVertice){
        this.elem = elem;
        this.sigVertice = sigVertice;
    }

    public Object getElem(){
        return elem;
    }

    public void setElem(Object elem){
        this.elem = elem;
    }

    public NodoVert getSigVertice(){
        return sigVertice;
    }

    public void setSigVertice(NodoVert sigVertice){
        this.sigVertice = sigVertice;
    }

    public NodoAdy getPrimerAdy(){
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy){
        this.primerAdy = primerAdy;
    }

}
