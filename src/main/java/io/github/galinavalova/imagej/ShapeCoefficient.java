package io.github.galinavalova.imagej;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.measure.ResultsTable;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import java.awt.*;

public class ShapeCoefficient implements PlugIn {

    @Override
    public void run(String arg) {
        ImagePlus currentImage = IJ.getImage();
        ImageProcessor ip = currentImage.getProcessor();

        int width = ip.getWidth();
        int height = ip.getHeight();

        Roi roi = currentImage.getRoi();

        if (roi == null) {
            IJ.showMessage("Error", "Create ROI before Shape Coefficient plugin start");
            return;
        }

        int roiType = roi.getType();
        boolean supportedRoi = roiType == Roi.RECTANGLE ||
                roiType == Roi.OVAL ||
                roiType == Roi.POLYGON ||
                roiType == Roi.FREEROI;

        if (! supportedRoi) {
            IJ.showMessage("Unsupported ROI", "Please select a closed area ROI\n" +
                    "(polygon, freehand, oval, or rectangle).");
            return;}

        int roiPixelsNumber = 0;
        double sumX = 0.0;
        double sumY = 0.0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (roi.contains(x, y)) {
                    sumX += x;
                    sumY += y;
                    roiPixelsNumber++;

                }
            }
        }

        double xCenter = sumX / roiPixelsNumber;
        double yCenter = sumY / roiPixelsNumber;
        double maxLength = getMaxLength(roi, xCenter, yCenter);
        double shapeCoefficient = roiPixelsNumber / maxLength;

        showResultTable(roiPixelsNumber, shapeCoefficient);
        }

    private static double getMaxLength(Roi roi, double xCenter, double yCenter) {
        Polygon boundary = roi.getPolygon();
        int[] xBoundaryArray = boundary.xpoints;
        int[] yBoundaryArray = boundary.ypoints;

        double maxLength = -1.0;

        for (int i = 0; i < xBoundaryArray.length; i++) {
            double length = Math.pow(xBoundaryArray[i] - xCenter, 2) + Math.pow(yBoundaryArray[i] - yCenter, 2);

            if (length > maxLength) {
                maxLength = length;
            }

        }
        return maxLength;
    }

    private void showResultTable(double area, double coefficient) {
        ResultsTable resultTable = ResultsTable.getResultsTable();
        resultTable.incrementCounter();
        resultTable.addValue("ROI area (px)", area);
        resultTable.addValue("Shape coefficient", coefficient);
        resultTable.show("Results");

    }
}
