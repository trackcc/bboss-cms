
package com.frameworkset.dictionary.tag;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

import com.frameworkset.dictionary.ProfessionDataManagerException;
import com.frameworkset.util.StringUtil;

/**
 * @author biaoping.yin
 * 2004-8-5
 */

public class XMLItemnameTag extends XMLBaseTag
{
	String itemValue;
	protected String getItemNames(Object t_value) throws ArrayIndexOutOfBoundsException, IllegalArgumentException, ProfessionDataManagerException
	{
		StringBuffer buffer = new StringBuffer();
		if(t_value.getClass().isArray())
		{
			int size = Array.getLength(t_value);
			for(int i = 0; i < size; i ++)
			{
				if(i == 0)
					buffer.append(data.getItemName(String.valueOf(Array.get(t_value, i))));
				else
					buffer.append(",").append(data.getItemName(String.valueOf(Array.get(t_value, i))));
			}
		}
		else if(t_value instanceof Collection)
		{
			Collection c_values = (Collection)t_value;
		
			Iterator it = c_values.iterator();
			int i = 0;
			while(it.hasNext())
			{
				if(i == 0)
					buffer.append(data.getItemName(String.valueOf(it.next())));
				else
					buffer.append(",").append(data.getItemName(String.valueOf(it.next())));
//				t_values.add(String.valueOf(it.next()));
				i ++;
			}
		}
		else if(t_value instanceof Iterator)
		{
			Iterator it = (Iterator)t_value;
			int i = 0;
			while(it.hasNext())
			{
				if(i == 0)
					buffer.append(data.getItemName(String.valueOf(it.next())));
				else
					buffer.append(",").append(data.getItemName(String.valueOf(it.next())));
				i ++;
//				t_values.add(String.valueOf(it.next()));
			}
		}
		else if(t_value instanceof String)
		{
			String defaultValues = (String)t_value;
			 if(defaultValues == null|| defaultValues.equals("") )
			        return null;
		    else
		    {
		    	String[] vs = StringUtil.split(defaultValues,"#$");
		    	int i = 0;
		        for(String v:vs)
	        	{
		        	if(i == 0)
						buffer.append(data.getItemName(v));
					else
						buffer.append(",").append(data.getItemName(v));
					i ++;
	        	}
		        
		    }
		}
		else
		{
			return data.getItemName((String.valueOf(t_value)));
		}
		return buffer.toString();
	}
	/* (non-Javadoc)
	 * @see com.westerasoft.common.tag.BaseTag#generateContent()
	 */
	public String generateContent()
	{
		if(data != null)
		{
			try
			{
	            if(getItemValue() != null)
	            {
//	                return data.getItemName(getItemValue());
	            	return getItemNames(getItemValue());
	            }
	            else if(t_value != null)
	            {
//	                return data.getItemName(t_value);
	            	return getItemNames(t_value);
	            }
			}
			catch (ProfessionDataManagerException e)
			{
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			return defaultName;
		}
		else
		{
			System.out.println("字典[" + this.type + "]不存在");
			return this.defaultName;
		}
	}


	/**
	 * @return
	 */
	public String getItemValue()
	{
		return itemValue;
	}

	/**
	 * @param string
	 */
	public void setItemValue(String string)
	{
		itemValue = string;
	}


	

}
