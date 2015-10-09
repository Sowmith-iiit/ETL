package testPackage;
import org.pentaho.di.trans.steps.csvinput.CsvInputMeta;
import java.security.InvalidKeyException;
import org.mortbay.util.ajax.JSON;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.StepLogTable;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.csvinput.CsvInputMeta;
import org.pentaho.di.trans.steps.fixedinput.FixedInputMeta;
import org.pentaho.di.trans.steps.olapinput.OlapInputMeta;
import org.pentaho.di.trans.steps.xmlinputstream.XMLInputStreamMeta; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Sample {
	public static void main(String[] args) {
		String obj = "{\"0\":{\"stepname\":\"Hello11\",\"input\":\"csv\",\"Filename\":\"input11.csv\",\"Delimiter\":\",\",\"Enclosure\":\"\",\"NIO_Buffer_size\":\"1000\",\"Lazy_conversion?\":true,\"Header_row_present?\":true,\"Add_filename_to_result\":true,\"The_row_number_field_name_(optional)\":\"\",\"Running_in_parallel?\":false,\"New_line_possible_in_fields?\":true,\"File_encoding\":\"utf-32\",},\"1\":{\"stepname\":\"Hello\",\"input\":\"csv\",\"Filename\":\"input.csv\",\"Delimiter\":\",\",\"Enclosure\":\"\",\"NIO_Buffer_size\":\"1000\",\"Lazy_conversion?\":true,\"Header_row_present?\":true,\"Add_filename_to_result\":true,\"The_row_number_field_name_(optional)\":\"\",\"Running_in_parallel?\":false,\"New_line_possible_in_fields?\":true,\"File_encoding\":\"utf-32\",},}";
		Integer i=new Integer(0);
		JSONParser parser=new JSONParser();
		TransMeta transMeta = new TransMeta();
		transMeta.setName("test1");
		Object obj1=new Object();
		try {
			obj1 = parser.parse(obj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj2 =(JSONObject) obj1;
		StepMeta fromstep=new StepMeta(),fromstep1=new StepMeta();
		for(i=0;i<2;i++)
		{
			String s = Integer.toString(i);
			JSONObject temp=new JSONObject();
			temp=(JSONObject)obj2.get(s);
			if(temp!=null)
			{
				String temp1=(String) temp.get("input");
				if(temp1.equals("csv"))
				{
					if(i==0)
					{
						CsvInputMeta cs = new  CsvInputMeta();
						cs = csv(temp.toString(),cs);
						fromstep = new StepMeta("CsvInput",(String)temp.get("stepname") ,(StepMetaInterface) cs);
						fromstep.setLocation(50, 100);
						fromstep.setDraw(true);
						fromstep.setDescription("");
						transMeta.addStep(fromstep);
					}
					else
					{
						CsvInputMeta cs1 = new  CsvInputMeta();
						cs1 = csv(temp.toString(),cs1);
						fromstep1 = new StepMeta("CsvInput",(String)temp.get("stepname") ,(StepMetaInterface) cs1);
						fromstep1.setLocation(150, 100);
						fromstep1.setDraw(true);
						fromstep1.setDescription("");
						
						transMeta.addStep(fromstep1);
						TransHopMeta hi = new TransHopMeta(fromstep, fromstep1);
						transMeta.addTransHop(hi);
						fromstep=fromstep1;
					}
				}	
			}
		}
		try {
			transMeta.writeXML("/home/sowmith/new.ktr");
			System.out.println("Done\n");
		}
		catch (KettleXMLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
	
	public static CsvInputMeta csv(String s ,CsvInputMeta csv){
		JSONParser parser=new JSONParser();
		JSONObject obj2=new JSONObject();
        try {
			Object obj = parser.parse(s);
			obj2 =(JSONObject) obj;				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		csv.setFilename((String)obj2.get("Filename"));
		csv.setDelimiter((String)obj2.get("Delimiter"));
		csv.setEnclosure((String)obj2.get("Enclosure"));
		csv.setBufferSize((String)obj2.get("NIO_Buffer_size"));
		csv.setLazyConversionActive(true);
		csv.setHeaderPresent(true);
		csv.setAddResultFile(true);
		csv.setRunningInParallel(true);
		csv.setRowNumField((String)obj2.get("The_row_number_field_name_(optional)"));
		csv.setNewlinePossibleInFields(true);
		csv.setEncoding((String)obj2.get("File_encoding"));
		return csv;
   }

    public static FixedInputMeta fixed(String s,FixedInputMeta fixed){
    	JSONParser parser=new JSONParser();
    	JSONObject obj2=new JSONObject();
    	try{
    		Object obj = parser.parse(s);
    		obj2 = (JSONObject) obj;
    	} catch (ParseException e){
    		e.printStackTrace();
    	}
    	fixed.setFilename((String)obj2.get("Filename"));
    	fixed.setLineWidth((String)obj2.get("Line_width_in_bytes"));
    	fixed.setLineFeedPresent(true);
    	fixed.setBufferSize((String)obj2.get("NIO_Buffer_size"));
    	fixed.setLazyConversionActive(true);
    	fixed.setHeaderPresent(true);
    	fixed.setRunningInParallel(true);
    	fixed.setEncoding((String)obj2.get("File_encoding"));
    	fixed.setAddResultFile(true);
		return fixed;
	}

	public static XMLInputStreamMeta xml(String s,XMLInputStreamMeta xml){
		JSONParser parser=new JSONParser();
    	JSONObject obj2=new JSONObject();
    	try{
    		Object obj = parser.parse(s);
    		obj2 = (JSONObject) obj;
    	} catch (ParseException e){
    		e.printStackTrace();
    	}
    	xml.setFilename((String)obj2.get("Filename"));
    	xml.setAddResultFile(true);
    	xml.setNrRowsToSkip((String)obj2.get("Skip"));
    	xml.setRowLimit((String)obj2.get("Limit"));
    	xml.setDefaultStringLen((String)obj2.get("Default_String_Length"));
    	xml.setEncoding((String)obj2.get("Encoding"));
    	xml.setEnableNamespaces(true);
    	xml.setEnableTrim(true);
    	xml.setIncludeFilenameField(true);
    	xml.setFilenameField((String)obj2.get("Fieldname"));
    	xml.setIncludeRowNumberField(true);
    	xml.setRowNumberField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlDataTypeNumericField(true);
    	xml.setXmlDataTypeNumericField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlLocationLineField(true);
    	xml.setXmlLocationLineField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlLocationColumnField(true);
    	xml.setXmlLocationColumnField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlElementIDField(true);
    	xml.setXmlElementIDField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlParentElementIDField(true);
    	xml.setXmlParentElementIDField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlElementLevelField(true);
    	xml.setXmlElementLevelField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlPathField(true);
    	xml.setXmlPathField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlParentPathField(true);
    	xml.setXmlParentPathField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlDataNameField(true);
    	xml.setXmlDataNameField((String)obj2.get("Fieldname"));
    	xml.setIncludeXmlDataValueField(true);
    	xml.setXmlDataValueField((String)obj2.get("Fieldname"));
    	return xml;
	}

	public static OlapInputMeta olap(String s,OlapInputMeta olap)
	{
		JSONParser parser=new JSONParser();
    	JSONObject obj2=new JSONObject();
    	try{
    		Object obj = parser.parse(s);
    		obj2 = (JSONObject) obj;
    	} catch (ParseException e){
    		e.printStackTrace();
    	}
    	olap.setOlap4jUrl((String)obj2.get("Url"));
    	olap.setUsername((String)obj2.get("Username"));
    	olap.setPassword((String)obj2.get("Password"));
    	olap.setVariableReplacementActive(true);
    	olap.setCatalog((String)obj2.get("Catalog"));
    	return olap;
	}





















}
