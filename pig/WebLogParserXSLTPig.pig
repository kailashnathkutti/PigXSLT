register /usr/lib/gphd/pig/piggybank.jar;
register /usr/lib/gphd/pig/lib/kiin_udf-0.0.1-SNAPSHOT.jar;

DEFINE customXSLTransform com.kiin.udf.XSLTranformer();

A = load '/agent01012014.log' using org.apache.pig.piggybank.storage.XMLLoader('ns:instance') as (x: chararray);
B = foreach A GENERATE FLATTEN(customXSLTransform(x) ) AS (name:chararray);
C =  filter B BY NOT $0 IS NULL;

store C into '/output';
