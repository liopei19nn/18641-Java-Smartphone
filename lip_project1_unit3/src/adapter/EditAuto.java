/**
 * 
 */
package adapter;

import scale.EditOptionEnum;

/**
 * @author Li Pei
 * @andrew_id lip 
 */
public interface EditAuto {
	/*
	 * edit auto mobile with with multithread
	 * 
	 * update option set name
	 */

	public void edit(EditOptionEnum editOptionEnum,String[] args);

}
