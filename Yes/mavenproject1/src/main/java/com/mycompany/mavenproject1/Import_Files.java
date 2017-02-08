/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ehsan
 */
public interface Import_Files {
    
    public void ImportIMG_PATH();
    public void ExecuteSQL(ArrayList<Statement> stmts);
    
}
