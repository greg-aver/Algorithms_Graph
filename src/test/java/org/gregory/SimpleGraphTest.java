package org.gregory;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

class SimpleGraphTest {

    @Test
    void addVertex_oversize() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(1);
        graph.AddVertex(1);
        graph.AddVertex(1);
        graph.AddVertex(1);
        graph.AddVertex(1);
        graph.AddVertex(1);
        graph.AddVertex(1);
    }

    @Test
    void addVertex_addMax() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        try {
            Field field = graph.getClass().getDeclaredField("vertex");
            field.setAccessible(true);
            Vertex[] vertex = (Vertex[]) field.get(graph);
            assertThat(vertex[0].getValue(), is(0));
            assertThat(vertex[1].getValue(), is(1));
            assertThat(vertex[2].getValue(), is(2));
            assertThat(vertex[3].getValue(), is(3));
            assertThat(vertex[4].getValue(), is(4));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void removeVertex_noVertex() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.RemoveVertex(3);
        graph.RemoveVertex(30);
    }

    @Test
    void removeVertex() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.RemoveVertex(4);
        try {
            Field field = graph.getClass().getDeclaredField("vertex");
            field.setAccessible(true);
            Vertex[] vertex = (Vertex[]) field.get(graph);
            assertThat(vertex[4], is(nullValue()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void isEdge_edgeTrue() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddEdge(1, 4);
        assertThat(graph.IsEdge(1, 4), is(true));
    }

    @Test
    void isEdge_noEdge() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        assertThat(graph.IsEdge(0, 4), is(false));
    }

    @Test
    void isEdge_noVertex() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        assertThat(graph.IsEdge(0, 4), is(false));
    }

    @Test
    void addEdge() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddEdge(1,2);
        try {
            Field field = graph.getClass().getDeclaredField("m_adjacency");
            field.setAccessible(true);
            int[][] m_adjacency = (int[][]) field.get(graph);
            assertThat(m_adjacency[1][2], is(1));
            assertThat(m_adjacency[2][1], is(1));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void removeEdge() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddEdge(1,2);
        graph.RemoveEdge(1,2);
        try {
            Field field = graph.getClass().getDeclaredField("m_adjacency");
            field.setAccessible(true);
            int[][] m_adjacency = (int[][]) field.get(graph);
            assertThat(m_adjacency[1][2], is(0));
            assertThat(m_adjacency[2][1], is(0));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void addEdge_noVertex() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(0);
        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddEdge(0, 4);
        graph.AddEdge(0, 40);
    }


}