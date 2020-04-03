# CircuiTikZ Tool
This project is designed to be an open-source tool for creating schematics with CircuiTikz. 
The goal of this project isn't to do everything with CircuiTikz, just handle the tedious parts of creating circuits in LaTex. 
Component labels, display parameters, and other configuration options are editable by the user through text fields. 
Currently most of the basic functionality is in the program with the exception of file input/output and some display parameters being editable in the UI. 

Currently Supported Components (Pre-release 0.0.3): 
- Path (wire) 
- Resistors
- Capacitors
- Inductors
- Diodes
- Voltage Source (DC)
- Current Source (DC)
- GND
- VCC
- VSS
- NPN Transistor
- PNP Transistor
- N-Mos Transistor
- P-Mos Transistor 

(Technically these are just the components with a "template" in the editor, parameters can be edited freely in the program such that most components available in CircuiTikz are useable)

## Running CircuiTikZ Tool
A lot of work still needs to be done before adding a full release to this repo.
For the time being there are runnable pre-releases available under the "releases" tab of this project
This project is still young so if you find any bugs while running the program please add them to the issue tracker.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details
