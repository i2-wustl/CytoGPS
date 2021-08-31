CytoGenetic Pattern Sleuth (CytoGPS) is a software tool to parse semi-structured ISCN-based karyotypes in text formats. This Java-based software was developed using ANTLR (ANother Tool for Language Recognition). A copy of the ANTLR jar file has been put inside the resource folder.

Note: The current code has not been fully cleaned. A section of code has not been completed. Several bugs were found but have not yet been fixed. All these will be expected to be completed in the next release.



How to Run the CytoGPS Software

After downloading the code and opening the project, find the file Run.java within the main package. Replace the six placeholders in the code with the following values:
i) The "[path_to_the_karyotype_input_file]" is where your karyotypes input is saved, e.g. "C:\\Karyotype_samples.txt"
ii) The "[path_to_the_output_JSON_file]" is where you want to save the JSON output, e.g. "C:\\Karyotype_samples.json"
iii) The "[path_to_the_output_aggregate_LGF_csv_file]" is where you want to save the aggregate LGF output, e.g. "C:\\Karyotype_samples.csv"
iv) The "[path_to_the_output_summary_statistics_csv_file]" is where you want to save the summary statistics output, e.g. "C:\\Karyotype_samples_SummaryStatistics.csv"

After you run the software, the console will print out the two timestamps; one is the starting time and the other is the completion time. For example:
	2021-08-30T14:40:08.098
	Start
	2021-08-30T14:40:08.155
	Complete
When you see this, you should be able to find the three output files written and saved into the folder you identify: the JSON output, the aggregate LGF output, and the summary statistics output.



Instructions on the Karyotypes Input File

We suggest you save all the karyotypes input in a .txt file, following the instructions as follows:
i) Each karyotype should be on a seperate line.
ii) Per convention, a multiple-clone karyotype is regarded a valid single karyotype, e.g., 47,XX,+8[25]/47,XX,+21[25].
iii) You should not insert any line separator inside a karyotype, especially for long multiple-clone karyotypes.



How to use the JSON output

The schema of JSON file is as follows:
{
     "producer": "CytoGPS",
     "date": "YYYY-MM-dd",
     "iscn2016_bands": [], // 916 (Sub)bands according to ISCN 2016 
     "output": [
         // Example of correct karyotype
         {
              "karyotype": XX, 
              "status": "Success",
              "parsing_result": [
                   {
                        "cell_number": XX, // Optional
                        "relationship": XX, // Optional
                        "uncertain_events": [], // Optional
                        "derivative_chromosome_detailed_systems": [], // Optional 
                        "loss_gain_fusion_computing": {
                             "loss": [], // Band_Loss_Counts
                             "gain": [], // Band_Gain_Counts
                             "fusion": []  // Band_Fusion_Counts
                        }
  
                   },
                   ... // Each object corresponds to a clone
              ]
         },
         // Example of incorrect karyotype with unfixable ISCN grammar errors
         {
              "karyotype": XX, 
              "status": "Nonfixable grammar error",
              "grammar_error": []
         },
         // Example of incorrect karyotype with fixable ISCN grammar errors and revised karyotype contains validation errors
         {
              "karyotype": XX, 
              "status": "Fixable grammar error but containing validation error",
              "grammar_error": [],
              "revised_karyotype": XX,
              "validation_error": []
         },
         // Example of incorrect karyotype with fixable ISCN grammar errors and revised karyotype is correct
         {
              "karyotype": XX, 
              "status": "Fixable grammar error and success",
              "grammar_error": [],
              "revised_karyotype": XX,
              "parsing_result": [
                   {
                        "cell_number": XX, // Optional
                        "relationship": XX, // Optional
                        "uncertain_events": [], // Optional
                        "derivative_chromosome_detailed_systems": [], // Optional 
                        "loss_gain_fusion_computing": {
                             "loss": [], // Band_Loss_Counts
                             "gain": [], // Band_Gain_Counts
                             "fusion": []  // Band_Fusion_Counts
                        }
  
                   },
                   ... // Each object corresponds to a clone
              ]
         },
         // Example of incorrect karyotype without fixable ISCN grammar errors which contains validation errors 
         {
              "karyotype": XX, 
              "status": "Validation error",
              "validation_error": []
         }

     ]
}



How to use Aggregate Loss-Gain-Fusion

To help biomedical data scientists conduct further analyses, we provide an aggregate csv file, which contains biologically important quantitative data, i.e., a tally of the cytogenetic events (loss, gain, and fusion) occurring at all G-850 bands (or subbands) in the chromosome. Several comments are worth noting:
i) Data is calculated at the clone level; a typical karyotype without subclones is regarded as a single-clone karyotype.
ii) Data is calculated for both a) correct karyotypes and b) the karyotypes which contain fixable grammar errors but contain no validation errors after the corrections the software has applied.
iii) The Karyotype_Revised column indicates whether the Loss-Gain-Fusion analysis is based on the original input or revised karyotype.
iv) For more detailed results of each line, please open the folder of the corresponding line number; each line has its own folder, even if that line has a karyotype which contains either a) nonfixable grammar errors or b) fixable grammar errors and validation errors after the corrections the software has applied.



How to use Summary Statistics

We provide summary statistics to help researchers understand the aberrancy percentage (AP) of the biological events (loss, gain, and fusion) for all chromosome bands or subbands. That is, for each chromosome band and/or subband, three aberrancy percentages will be calculated:
i) aberrancy percentage of loss (APL),
ii) aberrancy percentage of gain (APG), and
iii) aberrancy percentage of fusion (APF).

To be specific, we calculate each of these three percentages as follows:

Step 1: Count the total number of parsable clones (TNPC).
The number of clones contained in each karyotype will be added to TNPC, unless the karyotype contains unfixable grammar errors or validation errors.

Step 2: Count the total aberrancy number of the biological events (loss, gain, and fusion) for all chromosome bands or subbands.
For each chromosome band or subband, we increase its total aberrancy number of loss (TANL) by one, only if the number of loss occurring at this band or subband in a Loss-Gain-Fusion clone-specific result is greater than 0. Total aberrancy number of gain (TANG) and total aberrancy number of fusion (TANF) are calculated in a similar logic.

Step 3: Calculate the aberrancy percentages: APL, APG, and APF.
For each chromosome band or subband, calculate the three aberrancy percentages as below:
i) APL = TANL / TNPC,
ii) APG = TANG / TNPC, and
iii) APF = TANF / TNPC.


