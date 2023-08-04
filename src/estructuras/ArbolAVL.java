package estructuras;

import model.Desafio;

public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable clave) {
        /*
         * Devuelve verdadero si la clave recibida por parametro esta en el arbol y
         * falso en caso contrario.
         */
        boolean exito = false;
        if (!esVacio()) {
            exito = perteneceAux(this.raiz, clave);
        }
        return exito;
    }

    private boolean perteneceAux(NodoAVL nodo, Comparable clave) {
        boolean exito = false;
        int res = nodo.getClave().compareTo(clave);
        if (res == 0) {
            exito = true;
        } else {
            if (res < 0) {
                if (nodo.getDerecho() != null) {
                    exito = perteneceAux(nodo.getDerecho(), clave);
                }
            } else {
                if (nodo.getIzquierdo() != null) {
                    exito = perteneceAux(nodo.getIzquierdo(), clave);
                }
            }
        }
        return exito;
    }

    public boolean insertar(Comparable clave, Object elem) {
        boolean exito = false;
        if (esVacio()) {
            this.raiz = new NodoAVL(clave, elem, null, null);
            exito = true;
        } else {
            exito = insertarAux(this.raiz, clave, elem, null, null);
            this.raiz.recalcularAltura();
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL nodo, Comparable clave, Object elem, NodoAVL padre, NodoAVL padreAux) {
        boolean exito = true;
        int compar = clave.compareTo(nodo.getClave());
        if (compar == 0) {
            // Elemento repetido
            exito = false;
        } else {
            padreAux = padre;
            padre = nodo;
            if (compar < 0) {
                // Elem es menor a nodo.getElem().
                // Si tiene HI baja a la izquierda, sino agrega elem.
                if (nodo.getIzquierdo() != null) {
                    exito = insertarAux(nodo.getIzquierdo(), clave, elem, padre, padreAux);
                    nodo.recalcularAltura();
                    if (Math.abs(balance(nodo)) > 1) {
                        NodoAVL aux = balancear(balance(nodo), nodo);
                        if (nodo.getClave().equals(this.raiz.getClave())) {
                            this.raiz = aux;
                        } else {
                            if(aux.getClave().compareTo(padreAux.getClave()) < 0){
                                padreAux.setIzquierdo(aux);
                            } else {
                                padreAux.setDerecho(aux);
                            }
                            nodo.recalcularAltura();
                        }
                    }
                } else {
                    nodo.setIzquierdo(new NodoAVL(clave, elem, null, null));
                    nodo.recalcularAltura();
                }
            } else {
                // Elemento mayor que nodo.getElem().
                // Si tiene HD baja a la derecha, sino agrega elem.
                if (nodo.getDerecho() != null) {
                    exito = insertarAux(nodo.getDerecho(), clave, elem, padre, padreAux);
                    nodo.recalcularAltura();
                    if (Math.abs(balance(nodo)) > 1) {
                        NodoAVL aux = balancear(balance(nodo), nodo);
                        if (nodo.getClave().equals(this.raiz.getClave())) {
                            this.raiz = aux;
                        } else {
                            if(aux.getClave().compareTo(padreAux.getClave()) < 0){
                                padreAux.setIzquierdo(aux);
                            } else {
                                padreAux.setDerecho(aux);
                            }
                            nodo.recalcularAltura();
                        }
                    }
                } else {
                    nodo.setDerecho(new NodoAVL(clave, elem, null, null));
                    nodo.recalcularAltura();
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable clave) {
        boolean ret = false;
        if (pertenece(clave)) {
            ret = eliminarAux(this.raiz, clave, null, null);
            this.raiz.recalcularAltura();
        }
        return ret;
    }

    private boolean eliminarAux(NodoAVL nodo, Comparable clave, NodoAVL padre, NodoAVL padreAux) {
        int res = nodo.getClave().compareTo(clave);
        boolean ret = true;
        if (nodo != null) {
            if (res == 0) {
                // Caso 1: Sin hijos.
                if (nodo.getDerecho() == null && nodo.getIzquierdo() == null) {
                    caso1(padre, clave);
                } else {
                    // Caso 2: Con hijo derecho o izquierdo.
                    if (nodo.getDerecho() != null && nodo.getIzquierdo() == null) {
                        caso2(nodo, padre, 'D');
                    } else {
                        if (nodo.getIzquierdo() != null && nodo.getDerecho() == null) {
                            caso2(nodo, padre, 'I');
                        } else {
                            // Caso 3: con ambos hijos.
                            caso3(nodo);
                        }
                    }
                }
            } else {
                padreAux = padre;
                padre = nodo;
                if (res > 0) {
                    ret = eliminarAux(nodo.getIzquierdo(), clave, padre, padreAux);
                    nodo.recalcularAltura();
                    if (Math.abs(balance(nodo)) > 1) {
                        NodoAVL aux = balancear(balance(nodo), nodo);
                        if (nodo.getClave().equals(this.raiz.getClave())) {
                            this.raiz = aux;
                        } else {
                            padreAux.setIzquierdo(aux);
                            nodo.recalcularAltura();
                        }
                    }
                } else {
                    ret = eliminarAux(nodo.getDerecho(), clave, padre, padreAux);
                    nodo.recalcularAltura();
                    if (Math.abs(balance(nodo)) > 1) {
                        NodoAVL aux = balancear(balance(nodo), nodo);
                        if (nodo.getClave().equals(this.raiz.getClave())) {
                            this.raiz = aux;
                        } else {
                            padreAux.setDerecho(aux);
                            nodo.recalcularAltura();
                        }
                    }
                }

            }
        }
        return ret;
    }

    private void caso1(NodoAVL padre, Comparable clave) {
        // Eliminacion de hoja.
        if (padre == null) {
            this.raiz = null;
        } else {
            if (padre.getDerecho().getClave().compareTo(clave) == 0) {
                padre.setDerecho(null);
            } else {
                padre.setIzquierdo(null);
            }
        }
    }

    private void caso2(NodoAVL nodo, NodoAVL padre, char pos) {
        // Eliminacion con 1 hijo.
        /*
         * if(padre.getIzquierdo() != null &&
         * padre.getIzquierdo().getElem().compareTo(nodo.getElem()) == 0){ if (pos ==
         * 'D'){ padre.setIzquierdo(nodo.getDerecho()); } else {
         * padre.setIzquierdo(nodo.getIzquierdo()); } }else { if (pos == 'D'){
         * padre.setDerecho(nodo.getDerecho()); } else {
         * padre.setDerecho(nodo.getIzquierdo()); } }
         */
        if (pos == 'D') {
            if (padre.getIzquierdo() != null && padre.getIzquierdo().getClave().compareTo(nodo.getClave()) == 0) {
                padre.setIzquierdo(nodo.getDerecho());
            } else {
                padre.setDerecho(nodo.getDerecho());
            }
        } else {
            if (padre.getIzquierdo() != null && padre.getIzquierdo().getClave().compareTo(nodo.getClave()) == 0) {
                padre.setIzquierdo(nodo.getIzquierdo());
            } else {
                padre.setDerecho(nodo.getIzquierdo());
            }
        }
    }

    private void caso3(NodoAVL nodo) {
        // Eliminacion con dos hijos.
        NodoAVL aux, aux2 = null, padr = null;
        if (nodo.getIzquierdo().getDerecho() == null) {
            nodo.setClave(nodo.getIzquierdo().getClave());
            nodo.setIzquierdo(nodo.getIzquierdo().getIzquierdo());
        } else {
            aux = nodo.getIzquierdo();
            while (aux.getDerecho() != null) {
                padr = aux;
                aux = aux.getDerecho();
            }
            padr.setDerecho(null);
            nodo.setClave(aux.getClave());
        }
    }

    private int balance(NodoAVL nodo) {
        int balance = -1, altIzq = -1, altDer = -1;
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                altIzq = nodo.getIzquierdo().getAltura();
            }
            if (nodo.getDerecho() != null) {
                altDer = nodo.getDerecho().getAltura();
            }
            balance = altIzq - altDer;
        }
        return balance;
    }

    private NodoAVL balancear(int balance, NodoAVL nodo) {
        int balanceHijo;
        if (balance == 2) {
            balanceHijo = balance(nodo.getIzquierdo());
            if (balanceHijo == 1 || balanceHijo == 0) {
                nodo = rotarDerecha(nodo);
            } else {
                if (balanceHijo == -1) {
                    nodo = rotacionIzquierdaDerecha(nodo);
                }
            }
        } else {
            if (balance == -2) {
                balanceHijo = balance(nodo.getDerecho());
                if (balanceHijo == -1 || balanceHijo == 0) {
                    nodo = rotarIzquierda(nodo);
                } else {
                    if (balanceHijo == 1) {
                        nodo = rotacionDerechaIzquierda(nodo);
                    }
                }
            }
        }
        return nodo;
    }

    private NodoAVL rotarIzquierda(NodoAVL r) {
        NodoAVL h = r.getDerecho();
        NodoAVL temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotarDerecha(NodoAVL r) {
        NodoAVL h = r.getIzquierdo();
        NodoAVL temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        r.recalcularAltura();
        h.recalcularAltura();
        return h;
    }

    private NodoAVL rotacionIzquierdaDerecha(NodoAVL r) {
        r.setIzquierdo(rotarIzquierda(r.getIzquierdo()));
        return rotarDerecha(r);
    }

    private NodoAVL rotacionDerechaIzquierda(NodoAVL r) {
        r.setDerecho(rotarDerecha(r.getDerecho()));
        return rotarIzquierda(r);
    }

    public Lista listar() {
        /*
         * Recorre el árbol completo y devuelve una lista ordenada con los elementos que
         * se encuentran almacenados en él.
         */
        Lista ret = new Lista();
        listarAux(this.raiz, ret);
        return ret;
    }

    private void listarAux(NodoAVL nodo, Lista lis) {
        if (nodo != null) {
            listarAux(nodo.getIzquierdo(), lis);
            lis.insertar(nodo.getClave(), lis.longitud() + 1);
            listarAux(nodo.getDerecho(), lis);
        }
    }

    public Lista listarRango(Comparable elemMinimo, Comparable elemMaximo) {
        /*
         * Recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada con
         * los elementos que se encuentran en el intervalo [elemMinimo, elemMaximo].
         */
        Lista ret = new Lista();
        listarRangoAux(this.raiz, elemMinimo, elemMaximo, ret);
        return ret;
    }

    private void listarRangoAux(NodoAVL nodo, Comparable minim, Comparable maxim, Lista lis) {
        if (nodo != null) {
            if (nodo.getClave().compareTo(minim) > 0) {
                listarRangoAux(nodo.getIzquierdo(), minim, maxim, lis);
            }
            if (nodo.getClave().compareTo(minim) >= 0 && nodo.getClave().compareTo(maxim) <= 0) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
            if (nodo.getClave().compareTo(maxim) < 0) {
                listarRangoAux(nodo.getDerecho(), minim, maxim, lis);
            }
        }
    }

    public Comparable minimoElem() {
        /*
         * Recorre la rama correspondiente y devuelve el elemento más pequeño almacenado
         * en el árbol.
         */
        NodoAVL nodo = this.raiz;
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo.getClave();
    }

    public Comparable maximoElem() {
        /*
         * Recorre la rama correspondiente y devuelve el elemento más grande almacenado
         * en el árbol.
         */
        NodoAVL nodo = this.raiz;
        while (nodo.getDerecho() != null) {
            nodo = nodo.getDerecho();
        }
        return nodo.getClave();
    }

    public boolean esVacio() {
        /*
         * Devuelve falso si hay al menos un elemento en el árbol y verdadero en caso
         * contrario.
         */
        return this.raiz == null;
    }

    public void vaciar() {
        /*
         * Vacia la estructura.
         */
        this.raiz = null;
    }

    public ArbolAVL clone() {
        /*
         * Genera y devuelve un árbol binario que es equivalente (igual estructura y
         * contenido de los nodos) que el árbol original.
         */
        ArbolAVL clon = new ArbolAVL();
        if (!esVacio()) {
            NodoAVL nod = new NodoAVL(this.raiz.getClave(), this.raiz.getElem(), null, null);
            clon.raiz = nod;
            cloneAux(nod, this.raiz);
        }
        return clon;
    }

    private void cloneAux(NodoAVL aux, NodoAVL nodo) {
        if (nodo != null) {
            if (nodo.getIzquierdo() != null) {
                aux.setIzquierdo(new NodoAVL(nodo.getIzquierdo().getClave(), nodo.getIzquierdo().getElem(), null, null));
                cloneAux(aux.getIzquierdo(), nodo.getIzquierdo());
            }
            if (nodo.getDerecho() != null) {
                aux.setDerecho(new NodoAVL(nodo.getDerecho().getClave(), nodo.getDerecho().getElem(), null, null));
                cloneAux(aux.getDerecho(), nodo.getDerecho());
            }
        }
    }

    public String toString() {
        /*
         * Genera y devuelve una cadena de caracteres que indica cuál es la raíz del
         * árbol y quienes son los hijos de cada nodo.
         */
        String ret = "[]";
        if (!esVacio()) {
            ret = toStringAux(this.raiz, "");
        }
        return ret;
    }

    private String toStringAux(NodoAVL nodo, String str) {
        if (nodo != null) {
            NodoAVL izq = nodo.getIzquierdo(), der = nodo.getDerecho();
            str += "Nodo: " + nodo.getClave();
            if (izq != null && der != null) {
                str += " HI: " + izq.getClave();
                str += " HD: " + der.getClave() + "\n";
            } else {
                if (izq == null && der == null) {
                    str += " HI: - ";
                    str += " HD: - " + "\n";
                } else {
                    if (izq == null) {
                        str += " HI: -";
                        str += " HD: - " + der.getClave() + "\n";
                    } else {
                        if (der == null) {
                            str += " HI: " + izq.getClave();
                            str += " HD: - " + "\n";
                        }
                    }
                }
            }
            str = toStringAux(izq, str);
            str = toStringAux(der, str);
        }
        return str;
    }

    public Object encontrarElemento(Comparable clave) {
        /*
         * Devuelve el elemento recibido por parámetro si está en el árbol y
         * null en caso contrario.
         */
        Object ret = null;
        if (!esVacio()) {
            ret = encontrarElementoAux(this.raiz, clave);
        }
        return ret;
    }

    private Object encontrarElementoAux(NodoAVL nodo, Comparable clave) {
        Object ret = null;
        int res = nodo.getClave().compareTo(clave);
        if (res == 0) {
            ret = nodo.getElem();
        } else {
            if (res < 0) {
                if (nodo.getDerecho() != null) {
                    ret = encontrarElementoAux(nodo.getDerecho(), clave);
                }
            } else {
                if (nodo.getIzquierdo() != null) {
                    ret = encontrarElementoAux(nodo.getIzquierdo(), clave);
                }
            }
        }
        return ret;
    }

}