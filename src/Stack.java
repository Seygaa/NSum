
public class Stack {

    private int maxSize; // wielkość tablicy Stack
    private FunctionContext[] tab; // referencja do tablicy o elem. Params
    private int top; // wierzchołek stosu
    // --------------------------------------------------------------

    public Stack(int n) // konstruktor
    {
        maxSize = n; // określamy wielkość tablicy
        tab = new FunctionContext[maxSize]; // tworzymy tablicę
        top = -1; // na razie tablica jest pusta
    }

    public void push(FunctionContext p) // umieszczenie elementu na wierzchołku
    {
        tab[++top] = p;
    }

    // --------------------------------------------------------------
    public FunctionContext pop() // pobranie elementu z wierzchołka stosu
    {
        return tab[top--];
    }

    // --------------------------------------------------------------
    public FunctionContext top() // odczyt elementu z wierzchołka stosu
    { // bez usuwania elementu
        return tab[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}


