package me.model;
import me.math.Vector2f;
import me.math.Vector3f;

import java.util.*;

public class Model {

    public List<Vector3f> vertices;
    public List<Vector2f> textureVertices;
    public List<Vector3f> normals;
    public List<Polygon> polygons;

    public Model() {
        vertices = new ArrayList<>();
        textureVertices = new ArrayList<>();
        normals = new ArrayList<>();
        polygons = new ArrayList<>();
    }

    public Model(List<Vector3f> vertices, List<Vector2f> textureVertices, List<Vector3f> normals, List<Polygon> polygons) {
        this.vertices = vertices;
        this.textureVertices = textureVertices;
        this.normals = normals;
        this.polygons = polygons;
    }


}
