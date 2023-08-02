package estructuras;

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
        NodoVert aux = inicio;
        NodoVert nodoElim = null;
        NodoAdy ady = null;
        if(inicio != null){
            if(elem.equals(inicio.getElem())){
                // Si el vertice a eliminar es el inicio
                nodoElim = inicio;
                ady = nodoElim.getPrimerAdy();
                while(ady != null){
                    eliminarArco(nodoElim.getElem(), ady.getVertice().getElem());
                    ady = ady.getSigAdyacente();
                }
                inicio = inicio.getSigVertice();
                exito = true;
            } else {
                while(aux.getSigVertice() != null && !exito){
                    if(aux.getSigVertice().getElem().equals(elem)){
                        exito = true;
                        nodoElim = aux.getSigVertice();
                        ady = nodoElim.getPrimerAdy();
                        while(ady != null){
                            eliminarArco(nodoElim.getElem(), ady.getVertice().getElem());
                            ady = ady.getSigAdyacente();
                        }
                        aux.setSigVertice(nodoElim.getSigVertice());
                    } else {
                        aux = aux.getSigVertice();
                    }
                }
            }
        }
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
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        while(vertInicio != null && !exito){
            if(vertInicio.getElem().equals(origen)){
                nodoOrigen = vertInicio;
            } else {
                if(vertInicio.getElem().equals(destino)){
                    nodoDestino = vertInicio;
                }
            }
            vertInicio = vertInicio.getSigVertice();
            if(nodoOrigen != null && nodoDestino != null){
                nodoOrigen.setPrimerAdy(new NodoAdy(nodoDestino, nodoOrigen.getPrimerAdy(), etiqueta));
                nodoDestino.setPrimerAdy(new NodoAdy(nodoOrigen, nodoDestino.getPrimerAdy(), etiqueta));
                exito = true;
            }
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
        NodoVert nodoOrigen = null;
        NodoVert nodoDestino = null;
        while(vertInicio != null && !exito){
            if(vertInicio.getElem().equals(origen)){
                nodoOrigen = vertInicio;
            } else {
                if(vertInicio.getElem().equals(destino)){
                    nodoDestino = vertInicio;
                }
            }
            vertInicio = vertInicio.getSigVertice();
            if(nodoOrigen != null && nodoDestino != null){
                exito = eliminarNodoAdy(nodoOrigen, nodoDestino);
                eliminarNodoAdy(nodoDestino, nodoOrigen);
            }
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
                while (aux.getSigAdyacente() != null && !exito){
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

    /**
     * Con fines de debugging, este metodo genera y devuelve una cadena String que muestra los
     * vertices almacenados en el grafo y que adyacentes tiene cada uno de ellos.
     * @return
     */
    @Override
    public String toString(){
        String cadena = "";
        NodoVert vert;
        NodoAdy ady;
        if (inicio != null) {
            vert = inicio;
            while (vert != null) {
                cadena += vert.getElem() + " -> ";
                ady = vert.getPrimerAdy();
                while (ady != null) {
                    cadena += ady.getVertice().getElem() +"["+ady.getEtiqueta()+ "], ";
                    ady = ady.getSigAdyacente();
                }
                cadena = cadena.substring(0, cadena.length() - 1);
                cadena += "\n";
                vert = vert.getSigVertice();
            }
        }
        return cadena;
    }

    /**
     * Dado un elemento, devuelve una lista con los vertices adyacentes a el y su etiqueta.
     * @param clave  Comparable
     * @return ret
     */
    public String nodosContiguos(Comparable clave){
        String ret = "";
        NodoVert aux = encontrarVertice(clave);
        if(aux != null){
            NodoAdy nodoAdy = aux.getPrimerAdy();
            while(nodoAdy != null){
                ret += nodoAdy.getVertice().getElem() + " [" + nodoAdy.getEtiqueta() + "]\n";
                nodoAdy = nodoAdy.getSigAdyacente();
            }
        } else {
            ret = "El vertice no existe";
        }
        return ret;
    }

    /**
     * Dados dos elementos, devuelve la etiqueta del arco que los une.
     * @param elem
     * @param elem2
     * @return
     */
    public Object obtenerEtiqueta(Comparable elem,Comparable elem2){
        Object ret = null;
        NodoVert aux = encontrarVertice(elem);
        boolean exito = false;
        NodoAdy nodoAdy;

        if(aux != null){
            nodoAdy = aux.getPrimerAdy();
            while(nodoAdy != null && !exito){
                if(nodoAdy.getVertice().getElem().equals(elem2)){
                    exito = true;
                    ret = nodoAdy.getEtiqueta();
                } else {
                    nodoAdy = nodoAdy.getSigAdyacente();
                }
            }
        }
        return ret;
    }

    /**
     * Dados dos elementos, verifica si es posible llegar desde el primero al segundo con la cantidad de puntos dados.
     * @param origen
     * @param destino
     * @param puntos
     * @return
     */
    public boolean esPosibleLlegar(Object origen,Object destino, int puntos){
        boolean exito = false;

        if(!this.esVacio()){
            Lista visitados = new Lista();
            NodoVert aux = encontrarVertice(origen);
            NodoVert aux2 = encontrarVertice(destino);
            if(aux.getElem().equals(aux2.getElem())){
                exito = true;
            } else {
                exito = esPosibleLlegarAux(aux, aux2, puntos,visitados);
            }
        }
        return exito;
    }

    private boolean esPosibleLlegarAux(NodoVert origen,NodoVert destino, int puntos, Lista visitados){
        boolean exito = false;
        int acum = 0;
        if (origen != null){
            if(origen.getElem().equals(destino.getElem()) && puntos >= 0){
                exito = true;
            } else {
                visitados.insertar(origen.getElem(), visitados.longitud() + 1);
                NodoAdy nodoAdy = origen.getPrimerAdy();

                while(!exito && nodoAdy != null){
                    acum = (int) nodoAdy.getEtiqueta();
                    if(visitados.localizar(nodoAdy.getVertice().getElem()) < 0){
                        if(puntos - acum >= 0){
                            exito = esPosibleLlegarAux(nodoAdy.getVertice(), destino, puntos - acum, visitados);
                        }
                    }
                    nodoAdy = nodoAdy.getSigAdyacente();
                }
                visitados.eliminar(visitados.longitud());
            }
        }
        return exito;
    }

    /**
     * Dados dos elementos, devuelve una lista con los caminos posibles entre ellos, evitando un cierto nodo, acumulando k puntos.
     * @param origen
     * @param destino
     * @param evitado
     * @param puntos
     * @return
     */
    public Lista sinPasarPor(Object origen, Object destino, Object evitado, int puntos){
        Lista res = new Lista();
        Lista visitados = new Lista();

        if(!this.esVacio()){
            NodoVert aux = encontrarVertice(origen);
            NodoVert aux2 = encontrarVertice(destino);
            NodoVert aux3 = encontrarVertice(evitado);
            if(aux.getElem().equals(aux2.getElem())){
                res.insertar(origen, res.longitud()+1);
            } else {
                res = sinPasarPorAux(aux, aux2, aux3, puntos, visitados, res);
            }
        }
        return res;
    }

    private Lista sinPasarPorAux(NodoVert origen, NodoVert destino, NodoVert evitado, int puntos, Lista visitados, Lista res){
        int acum = 0;

        if (origen != null){
            visitados.insertar(origen.getElem(), visitados.longitud() + 1);

            if(origen.getElem().equals(destino.getElem()) && puntos >= 0){
                res.insertar(visitados.clone(), res.longitud() + 1);

            } else {
                NodoAdy nodoAdy = origen.getPrimerAdy();

                while(nodoAdy != null){
                    acum = (int) nodoAdy.getEtiqueta();

                    if(visitados.localizar(nodoAdy.getVertice().getElem()) < 0 && !nodoAdy.getVertice().getElem().equals(evitado.getElem())){
                        if(puntos - acum >= 0){
                            res = sinPasarPorAux(nodoAdy.getVertice(), destino, evitado, puntos - acum, visitados, res);
                        }
                    }
                    nodoAdy = nodoAdy.getSigAdyacente();
                }
            }
            visitados.eliminar(visitados.longitud());
        }
        return res;
    }

}
