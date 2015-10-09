package testPackage;
import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.pentaho.di.trans.steps.csvinput.CsvInputMeta;
import org.pentaho.di.trans.steps.fixedinput.FixedInputMeta;
import org.pentaho.di.trans.steps.olapinput.OlapInputMeta;
import org.pentaho.di.trans.steps.xmlinputstream.XMLInputStreamMeta; 

import java.security.InvalidKeyException;

import org.mortbay.util.ajax.JSON;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.logging.StepLogTable;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.csvinput.CsvInputMeta;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("")
public class TestServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	String obj=request.getParameter("gson");
	obj += '}';
	System.out.print(obj);
	String files=request.getParameter("file");
	files = "/home/sowmith/Downloads/apache-tomcat-7.0/webapps/Final" + files + ".ktr"; 
	response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    RequestDispatcher view = request.getRequestDispatcher("project.jsp");
    view.forward(request, response);
    TransMeta transMeta = new TransMeta();
	transMeta.setName("test1");
	Integer i=new Integer(0);
	JSONParser parser=new JSONParser();
	Object obj1=new Object();
	try {
		obj1 = parser.parse(obj);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	JSONObject obj2 =(JSONObject)obj1;
	StepMeta fromstep=new StepMeta();
	StepMeta fromstep1=new StepMeta();
	for(i=0;i<100;i++)
	{
		String s = Integer.toString(i);
		JSONObject temp=new JSONObject();
		temp=(JSONObject)obj2.get(s);
		if(temp!=null)
		{
			String temp1=(String)temp.get("input");
			if(temp1.equals("CSV"))
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
					fromstep1.setLocation((i+1)*50, 100);
					fromstep1.setDraw(true);
					fromstep1.setDescription("");
					transMeta.addStep(fromstep1);
					TransHopMeta hi = new TransHopMeta(fromstep, fromstep1);
					transMeta.addTransHop(hi);
					fromstep=fromstep1;
				}
			}
			if(temp1.equals("fixedfileinput"))
			{
				if(i==0)
				{
					FixedInputMeta cs = new  FixedInputMeta();
					cs = fixed(temp.toString(),cs);
					fromstep = new StepMeta("FixedInput",(String)temp.get("stepname") ,(StepMetaInterface) cs);
					fromstep.setLocation(50, 100);
					fromstep.setDraw(true);
					fromstep.setDescription("");
					transMeta.addStep(fromstep);
				}
				else
				{
					FixedInputMeta cs1 = new  FixedInputMeta();
					cs1 = fixed(temp.toString(),cs1);
					fromstep1 = new StepMeta("FixedInput",(String)temp.get("stepname") ,(StepMetaInterface) cs1);
					fromstep1.setLocation((i+1)*50, 100);
					fromstep1.setDraw(true);
					fromstep1.setDescription("");
					transMeta.addStep(fromstep1);
					TransHopMeta hi = new TransHopMeta(fromstep, fromstep1);
					transMeta.addTransHop(hi);
					fromstep=fromstep1;
				}
			}
			if(temp1.equals("xmlstream"))
			{
				if(i==0)
				{
					XMLInputStreamMeta cs = new XMLInputStreamMeta();
					cs = xml(temp.toString(),cs);
					fromstep = new StepMeta("XMLInputStream",(String)temp.get("stepname") ,(StepMetaInterface) cs);
					fromstep.setLocation(50, 100);
					fromstep.setDraw(true);
					fromstep.setDescription("");
					transMeta.addStep(fromstep);
				}
				else
				{
					XMLInputStreamMeta cs1 = new  XMLInputStreamMeta();
					cs1 = xml(temp.toString(),cs1);
					fromstep1 = new StepMeta("XMLInputStream",(String)temp.get("stepname") ,(StepMetaInterface) cs1);
					fromstep1.setLocation(50 + i*50, 100);
					fromstep1.setDraw(true);
					fromstep1.setDescription("");
					
					transMeta.addStep(fromstep1);
					TransHopMeta hi = new TransHopMeta(fromstep, fromstep1);
					transMeta.addTransHop(hi);
					fromstep=fromstep1;
				}
			}
			if(temp1.equals("Olap"))
			{
				if(i==0)
				{
					OlapInputMeta cs = new OlapInputMeta();
					cs = olap(temp.toString(),cs);
					fromstep = new StepMeta("OlapInput",(String)temp.get("stepname") ,(StepMetaInterface) cs);
					fromstep.setLocation(50, 100);
					fromstep.setDraw(true);
					fromstep.setDescription("");
					transMeta.addStep(fromstep);
				}
				else
				{
					OlapInputMeta cs1 = new  OlapInputMeta();
					cs1 = olap(temp.toString(),cs1);
					fromstep1 = new StepMeta("OlapInput",(String)temp.get("stepname") ,(StepMetaInterface) cs1);
					fromstep1.setLocation((i+1)*50, 100);
					fromstep1.setDraw(true);
					fromstep1.setDescription("");
					
					transMeta.addStep(fromstep1);
					TransHopMeta hi = new TransHopMeta(fromstep, fromstep1);
					transMeta.addTransHop(hi);
					fromstep=fromstep1;
				}
			}
		}
		else
		{
			break;
		}
	}
	try {
		transMeta.writeXML(files);
		System.out.println("Done\n");
	}
	catch (KettleXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
  }
	public static CsvInputMeta csv(String s ,CsvInputMeta csv){			
		csv.setFilename("Filename");
		csv.setDelimiter("Delimiter");
		csv.setEnclosure("Enclosure");
		csv.setBufferSize("NIO_Buffer_size");
		csv.setLazyConversionActive(true);
		csv.setHeaderPresent(true);
		csv.setAddResultFile(true);
		csv.setRunningInParallel(true);
		csv.setRowNumField("The_row_number_field_name_(optional)");
		csv.setNewlinePossibleInFields(true);
		csv.setEncoding("File_encoding");
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
