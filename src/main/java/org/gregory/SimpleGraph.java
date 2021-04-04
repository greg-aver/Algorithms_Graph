package org.gregory;


class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    private int vertexSize;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        if (getVertexSize() == max_vertex) {
            return;
        }
        vertex[getVertexSize()] = new Vertex(value);
        vertexSizeIncrement();
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        if (v < 0 || v >= getVertexSize()) {
            return;
        }
        vertex[v] = null;
        for (int i = 0; i < getVertexSize(); i++) {
            m_adjacency[v][i] = 0;
        }
        for (int i = 0; i < getVertexSize(); i++) {
            m_adjacency[i][v] = 0;
        }
        vertexSizeDecrement();
    }

    private boolean checkCorrectVertex(int v1, int v2) {
        if (v1 < 0 || v1 >= getMax_vertex() || v2 < 0 || v2 >= getMax_vertex()) {
            return false;
        }
        return true;
    }

    public boolean IsEdge(int v1, int v2) {
        if (!checkCorrectVertex(v1, v2)) {
            return false;
        }
        // true если есть ребро между вершинами v1 и v2
        if (m_adjacency[v1][v2] == 1) {
            return true;
        }
        return false;
    }

    public void AddEdge(int v1, int v2) {
        if (!checkCorrectVertex(v1, v2)) {
            return;
        }
        // добавление ребра между вершинами v1 и v2
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        if (!checkCorrectVertex(v1, v2)) {
            return;
        }
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    private void vertexSizeIncrement() {
        vertexSize++;
    }

    private void vertexSizeDecrement() {
        vertexSize--;
    }

    public int getMax_vertex() {
        return max_vertex;
    }

    public int getVertexSize() {
        return vertexSize;
    }
}