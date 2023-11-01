package me.objwriter;

import me.math.Vector2f;
import me.math.Vector3f;
import me.model.Model;
import me.model.Polygon;

import java.io.*;
import java.util.List;

public class ObjWriter {

    private static final String OBJ_VERTEX_STRING_TEMPLATE = "v %f %f %f" + System.lineSeparator();
    private static final String OBJ_TEXTURE_STRING_TEMPLATE = "vt %f %f" + System.lineSeparator();
    private static final String OBJ_NORMAL_STRING_TEMPLATE = "vn %f %f %f" + System.lineSeparator();

    public static void write(Model model, String filePath) throws IOException {
        File file = new File(filePath);
        write(model, file);
    }

    public static void write(Model model, File file) throws IOException {
        if (file.isFile() && file.getName().split("\\.")[1].equals("obj")) {
            try (Writer writer = new FileWriter(file)) {
                write(model, writer);
            }
        } else {
            throw new IOException("Get folder or not .obj file");
        }
    }

    public static void write(Model model, String fileFolder, String fileName) throws IOException {
        try (Writer writer = new FileWriter(fileFolder + "\\" + fileName + ".obj")) {
            write(model, writer);
        }
    }

    public static void write(Model model, Writer writer) throws IOException {
        writeVertices(model.vertices, writer);
        writeTextureVertices(model.textureVertices, writer);
        writeNormals(model.normals, writer);
        writeFaces(model.polygons, writer);
    }

    protected static void writeFaces(List<Polygon> polygons, Writer writer) throws IOException {
        for (Polygon polygon : polygons) {
            writer.write("f");

            for (int i = 0; i < polygon.getVertexIndices().size(); i++) {

                writer.write(" " + polygon.getVertexIndices().get(i));

                if (polygon.getTextureVertexIndices().size() > i) {
                    writer.write("/" + polygon.getTextureVertexIndices().get(i));
                    if (polygon.getNormalIndices().size() > i) {
                        writer.write("/" + polygon.getNormalIndices().get(i));
                    }
                } else {
                    if (polygon.getNormalIndices().size() > i) {
                        writer.write("//" + polygon.getNormalIndices().get(i));
                    }
                }
            }
            writer.write(System.lineSeparator());
        }
    }

    protected static void writeVertices(List<Vector3f> vertices, Writer writer) throws IOException {
        for (Vector3f v : vertices) {
            writer.write(String.format(OBJ_VERTEX_STRING_TEMPLATE, v.getX(), v.getY(), v.getZ()));
        }
    }

    protected static void writeTextureVertices(List<Vector2f> textureVertices, Writer writer) throws IOException {
        for (Vector2f vt : textureVertices) {
            writer.write(String.format(OBJ_TEXTURE_STRING_TEMPLATE, vt.getX(), vt.getY()));
        }
    }

    protected static void writeNormals(List<Vector3f> normals, Writer writer) throws IOException {
        for (Vector3f v : normals) {
            writer.write(String.format(OBJ_NORMAL_STRING_TEMPLATE, v.getX(), v.getY(), v.getZ()));
        }
    }
}
