# Dimensionless shape-based metric ImageJ Plugin

## Description
The dimensionless shape-based metric used in this plugin is based on the approach proposed in:

Valova G.S., Bogomyakova O.B., Tur D.A., Tulupov A.A., Cherevko A.A., Akulov A.E.  
*Mathematical model-based comparison of lateral ventricle geometry in laboratory mice and humans with hydrocephalus* (article under consideration in ...).

In this work, a geometric shape descriptor is used for quantitative comparison of brain ventricle morphology.
The implementation in this plugin is adapted for ROI-based analysis in ImageJ.

## Features
- Calculates ROI area (in pixels)
- Computes maximum distance from centroid to ROI boundary
- Computes shape coefficient based on sigmoid transformation
- Displays results in ImageJ Results Table

## Requirements
- ImageJ 1.x
- Java 8+

## Usage
1. Open an image in ImageJ
2. Select ROI (rectangle, oval, polygon, or freehand)
3. Run the plugin:
   Plugins → Analysis → ShapeCoefficient
4. View results in the Results window

## Output
- ROI area (px)
- Dimensionless shape coefficient

## Notes
The dimensionless shape-based metric is a custom metric and not a standard geometric descriptor.
