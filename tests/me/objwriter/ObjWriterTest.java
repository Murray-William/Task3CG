package me.objwriter;

import me.math.Vector2f;
import me.math.Vector3f;
import me.model.Model;
import me.model.Polygon;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ObjWriterTest {

    private static Model model;
    private static String modelToObjString;

    @BeforeAll
    static void init() {
        model =  new Model(new ArrayList<Vector3f>(List.of(new Vector3f(1.122f, 2, 3.2321f),
                new Vector3f(3.213f, 4.2f, 5),
                new Vector3f(6, 7.33f, 8.123213f),
                new Vector3f(60, -9.99f, 8.3f))),
                new ArrayList<Vector2f>(List.of(new Vector2f(1.123f, 2f),
                        new Vector2f(3.31f, 4.2f),
                        new Vector2f(0.21f, 14f),
                        new Vector2f(22.1f, 45f))),
                new ArrayList<Vector3f>(List.of(new Vector3f(1.122f, 2, -3.2321f),
                        new Vector3f(3.213f, 4.2f, 5),
                        new Vector3f(6, 7.33f, 8.123213f),
                        new Vector3f(12, 3.33f, 32f))),
                new ArrayList<Polygon>(List.of(new Polygon(List.of(1, 1, 1), List.of(2, 2, 2), List.of(3, 3, 3)),
                        new Polygon(List.of(2, 2, 2), List.of(3, 3, 3), List.of(4, 4, 4)))));

        modelToObjString = "v 1.122000 2.000000 3.232100" + System.lineSeparator() +
                "v 3.213000 4.200000 5.000000" + System.lineSeparator() +
                "v 6.000000 7.330000 8.123213" + System.lineSeparator() +
                "v 60.000000 -9.990000 8.300000" + System.lineSeparator() +
                "vt 1.123000 2.000000" + System.lineSeparator() +
                "vt 3.310000 4.200000" + System.lineSeparator() +
                "vt 0.210000 14.000000" + System.lineSeparator() +
                "vt 22.100000 45.000000" + System.lineSeparator() +
                "vn 1.122000 2.000000 -3.232100" + System.lineSeparator() +
                "vn 3.213000 4.200000 5.000000" + System.lineSeparator() +
                "vn 6.000000 7.330000 8.123213" + System.lineSeparator() +
                "vn 12.000000 3.330000 32.000000" + System.lineSeparator() +
                "f 1/2/3 1/2/3 1/2/3" + System.lineSeparator() +
                "f 2/3/4 2/3/4 2/3/4";
    }

    @Test
    void writeWithWriter() throws IOException {
        StringWriter sw = new StringWriter();
        ObjWriter.write(model, sw);

        assertEquals(modelToObjString + System.lineSeparator(), sw.toString());

        sw.close();
    }

    @Test
    void writeIntoFile() throws IOException {
        String objFilePath = "D:\\javaProjects\\course2Half1\\CG\\Task3CG\\tests\\resources\\objwriter\\output\\test.obj";

        ObjWriter.write(model, new File(objFilePath));

        assertEquals(modelToObjString, Files.lines(Paths.get(objFilePath)).collect(Collectors.joining(System.lineSeparator())));
    }

    @Test
    void writeWithFilePath() throws IOException {
        String objFilePath = "D:\\javaProjects\\course2Half1\\CG\\Task3CG\\tests\\resources\\objwriter\\output\\test.obj";

        ObjWriter.write(model,objFilePath);

        assertEquals(modelToObjString, Files.lines(Paths.get(objFilePath)).collect(Collectors.joining(System.lineSeparator())));
    }

    @Test
    void writeWithFileFolderPathAndName() throws IOException {
        String objFileFolderPath = "D:\\javaProjects\\course2Half1\\CG\\Task3CG\\tests\\resources\\objwriter\\output\\";
        String objFileName = "test1";

        ObjWriter.write(model,objFileFolderPath, objFileName);

        assertEquals(modelToObjString, Files.lines(Paths.get(objFileFolderPath + objFileName + ".obj")).collect(Collectors.joining(System.lineSeparator())));

    }
    @Test
    void writeVertices() throws IOException {
        List<Vector3f> vertices = List.of(new Vector3f(1.122f, 2, 3.2321f),
                new Vector3f(3.213f, 4.2f, 5),
                new Vector3f(6, 7.33f, 8.123213f));
        StringWriter writer = new StringWriter();

        ObjWriter.writeVertices(vertices, writer);

        assertEquals("v 1.122000 2.000000 3.232100" + System.lineSeparator() +
                        "v 3.213000 4.200000 5.000000" + System.lineSeparator() +
                        "v 6.000000 7.330000 8.123213" + System.lineSeparator()
                , writer.toString());

        writer.close();
    }

    @Test
    void writeTextureVertices() throws IOException {
        List<Vector2f> vt = List.of(new Vector2f(1.123f, 2f),
                new Vector2f(3.31f, 4.2f));
        StringWriter writer = new StringWriter();

        ObjWriter.writeTextureVertices(vt, writer);

        assertEquals("vt 1.123000 2.000000" + System.lineSeparator() +
                        "vt 3.310000 4.200000" + System.lineSeparator(),
                writer.toString());
        writer.close();
    }

    @Test
    void writeNormals() throws IOException {
        List<Vector3f> normals = List.of(new Vector3f(1.122f, 2, 3.2321f),
                new Vector3f(3.213f, 4.2f, 5),
                new Vector3f(6, 7.33f, 8.123213f));
        StringWriter writer = new StringWriter();

        ObjWriter.writeNormals(normals, writer);

        assertEquals("vn 1.122000 2.000000 3.232100" + System.lineSeparator() +
                        "vn 3.213000 4.200000 5.000000" + System.lineSeparator() +
                        "vn 6.000000 7.330000 8.123213" + System.lineSeparator()
                , writer.toString());

        writer.close();
    }

    @Test
    void writeFace() throws IOException {
        List<Polygon> polygons = List.of(new Polygon(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9)),
                new Polygon(List.of(1, 2, 3, 10, 12), List.of(4, 5, 6, 10, 15), List.of(7, 8, 9, 10, 16)),
                new Polygon(List.of(1, 2, 3), List.of(), List.of(7, 8, 9)),
                new Polygon(List.of(1, 2, 3), List.of(), List.of()),
                new Polygon(List.of(1, 2, 3), List.of(4, 5, 6), List.of()));
        StringWriter sw = new StringWriter();

        ObjWriter.writeFaces(polygons, sw);
        assertEquals(
                "f 1/4/7 2/5/8 3/6/9" + System.lineSeparator() +
                        "f 1/4/7 2/5/8 3/6/9 10/10/10 12/15/16" + System.lineSeparator() +
                        "f 1//7 2//8 3//9" + System.lineSeparator() +
                        "f 1 2 3" + System.lineSeparator() +
                        "f 1/4 2/5 3/6" + System.lineSeparator(), sw.toString()
        );
        sw.close();
    }
}










