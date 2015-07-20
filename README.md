MetadataMiner
=============

Java application for bulk extraction of Metadata structures from Microsoft Office documents.

Requirements:
=============

MetadataMiner requires a Java runtime environment. It has been tested with Oracle Java 6 and 7.

Downloading:
============

git clone https://github.com/ipduffy/MetadataMiner

Executing:
==========

Windows: double click on the .JAR file contained in the "dist" folder.

Linux: java -jar MetadataMiner.jar

Mac OS X: double click on the .JAR file or, from the command line, type java -jar MetadataMiner.jar

Getting started:
================

To run MetadataMiner, you will first need to create a Project Workspace. Click "New" if this is your first time running MetadataMiner. This will create a new Project Workspace (i.e. database) into which all of your document Metadata will be saved. Pick a folder and give the database a name.

Once you have set up your Project Workspace, click in the text box for "Working Directory". A file selection dialog will appear asking you to select the directory containing the documents to be parsed for metadata. Browse to and select the directory. NOTE: All subdirectories will be parsed.

When the appropriate directory has been chosen, click "Go" and MetadataMiner will parse all Microsoft Office files (both compound document files as well as Office Open XML files) contained in the Working Directory and all subdirectories.

Parsing does not take very long - it parses at a rate of around 1000 documents per minutee, although this is largely dependent on hardware. MetadataMiner has been tested on document repositories as large as 50,000+ documents. To parse this many documents took approximately 45 minutes. Smaller document collections should finish much more quickly.

Once MetadataMiner is completed parsing the documents in the Working Directory, the data collected can be searched using the search button. Reports can be created by clicking on the reports button.
