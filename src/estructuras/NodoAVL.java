package estructuras;

public class NodoAVL {

    private Comparable clave;
    private Object elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;

    public NodoAVL(Comparable clave,Object elem, NodoAVL izq, NodoAVL der) {
        this.clave = clave;
        this.elem = elem;
        this.izquierdo = izq;
        this.derecho = der;
        this.altura = 0;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public void setIzquierdo(NodoAVL izq) {
        this.izquierdo = izq;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setDerecho(NodoAVL der) {
        this.derecho = der;
    }

    public Comparable getClave() {
        return this.clave;
    }

    public Object getElem() {
        return this.elem;
    }

    public int getAltura() {
        return this.altura;
    }

    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }

    public NodoAVL getDerecho() {
        return this.derecho;
    }

    public void recalcularAltura() {
        int izq = -1, der = -1;
        if (this.izquierdo != null) {
            izq = this.izquierdo.altura;
        }
        if (this.derecho != null) {
            der = this.derecho.altura;
        }
        this.altura = (Math.max(izq, der)) + 1;
    }
}
