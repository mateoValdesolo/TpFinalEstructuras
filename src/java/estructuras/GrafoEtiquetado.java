package java.estructuras;

/**
 * @author Mateo Valdesolo
 */
public class GrafoEtiquetado {

    private NodoVert inicio;


    /**
     * Crea un grafo vacio
     */
    public GrafoEtiquetado(){
        inicio = null;
    }

    /**
     * Dado un elemento de TipoVertice se lo agrega a la estructura controlando que no se inserten
     * vertices repetidos. Si puede realizar la insercion devuelve verdadero, en caso contrario devuelve falso.
     * @param elem
     * @return exito
     */
    public boolean insertarVertice(Object elem){
        boolean exito = false;
        if(!existeVertice(elem)){
            inicio = new NodoVert(elem,inicio);
            exito = true;
        }
        return exito;
    }

    /**
     *  Dado un elemento de TipoVertice se lo quita de la estructura. Si se encuentra el vertice, tambien
     * deben eliminarse todos los arcos que lo tengan como origen o destino. Si se puede realizar la
     * eliminacion con éxito devuelve verdadero, en caso contrario devuelve falso
     * @param elem
     * @return exito
     */
    public boolean eliminarVertice(Object elem){
        boolean exito = false;


        return exito;
    }

    /**
     *  Dado un elemento, devuelve verdadero si esta en la estructura y falso en caso contrario.
     * @param elem
     * @return exito
     */
    public boolean existeVertice(Object elem){
        boolean exito = false;
        NodoVert aux = inicio;
        if(inicio != null){
            while(aux != null && !exito){
                if(aux.getElem().equals(elem)){
                    exito = true;
                } else {
                    aux = aux.getSigVertice();
                }
            }
        }
        return exito;
    }

    /**
     *  Dados dos elementos de TipoVertice (origen y destino) agrega el arco en la estructura, solo si
     * ambos vertices ya existen en el grafo. Si puede realizar la insercion devuelve verdadero, en caso
     * contrario devuelve falso.
     * @param origen
     * @param destino
     * @param etiqueta
     * @return exito
     */
    public boolean insertarArco(Object origen,Object destino,Object etiqueta){
        boolean exito = false;
        NodoVert vertInicio = inicio;
        NodoVert nodoOrigen = encontrarVertice(origen);
        NodoVert nodoDestino = encontrarVertice(destino);
        if(nodoOrigen != null && nodoDestino != null){
            nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(), etiqueta));
            nodoDestino.setPrimerAdy(new NodoAdy(nodoOrigen, nodoDestino.getPrimerAdy(), etiqueta));
            exito = true;
        }
        return exito;
    }

    /**
     *  Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
     * ambos vertices. Si el arco existe y se puede realizar la eliminación con exito devuelve verdadero, en
     * caso contrario devuelve falso
     * @param origen
     * @param destino
     * @return exito
     */
    public boolean eliminarArco(Object origen,Object destino){
        boolean exito = false;
        NodoVert vertInicio = inicio;
        NodoVert nodoOrigen = encontrarVertice(origen);
        NodoVert nodoDestino = encontrarVertice(destino);
        if(nodoOrigen != null && nodoDestino != null){
            exito = eliminarNodoAdy(nodoOrigen, nodoDestino) && eliminarNodoAdy(nodoDestino, nodoOrigen);
        }
        return exito;
    }

    /**
     *  Dados dos elementos de TipoVertice (origen y destino), devuelve verdadero si existe un arco en
     * la estructura que los une y falso en caso contrario.
     * @param origen
     * @param destino
     * @return exito
     */
    public boolean existeArco(Object origen,Object destino){
        boolean exito = false;
        NodoVert aux = encontrarVertice(origen);
        NodoAdy nodoAdy;
        if(aux != null){
            nodoAdy = aux.getPrimerAdy();
            while(nodoAdy != null && !exito){
                if(nodoAdy.getVertice().getElem().equals(destino)){
                    exito = true;
                } else {
                    nodoAdy = nodoAdy.getSigAdyacente();
                }
            }
        }
        return exito;
    }

    /**
     *  Devuelve falso si hay al menos un vertice cargado en el grafo y verdadero en caso contrario.
     * @return exito
     */
    public boolean esVacio(){
        return inicio == null;
    }

    /**
     *  Dado un elemento retorna el vertice el cual lo contiene.
     * @return elem
     */
    private NodoVert encontrarVertice(Object elem){
        NodoVert aux = inicio;
        while(aux != null && !aux.getElem().equals(elem)){
            aux = aux.getSigVertice();
        }
        return aux;
    }

    /**
     *  Dado dos Nodos Vertice, elimina el arco que los une.
     *  @param nodoOrigen
     *  @param nodoDestino
     *  @return exito
     */
    private boolean eliminarNodoAdy(NodoVert nodoOrigen, NodoVert nodoDestino){
        boolean exito = false;
        NodoAdy aux = nodoOrigen.getPrimerAdy();
        if(aux != null){
            if(aux.getVertice().getElem().equals(nodoDestino.getElem())){
                nodoOrigen.setPrimerAdy(nodoOrigen.getPrimerAdy().getSigAdyacente());
            } else {
                while (aux != null && !exito){
                    if(aux.getSigAdyacente().getVertice().getElem().equals(nodoDestino.getElem())){
                        aux.setSigAdyacente(aux.getSigAdyacente().getSigAdyacente());
                        exito = true;
                    } else {
                        aux = aux.getSigAdyacente();
                    }
                }
            }
        }
        return exito;
    }


}
