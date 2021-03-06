/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.requirements.importer.transposer.bridge.rules;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.diffmerge.bridge.mapping.api.IMappingExecution;
import org.eclipse.emf.diffmerge.bridge.mapping.api.IQueryExecution;
import org.eclipse.emf.diffmerge.bridge.util.structures.ITuple;
import org.eclipse.rmf.reqif10.DatatypeDefinition;
import org.eclipse.rmf.reqif10.DatatypeDefinitionEnumeration;
import org.eclipse.rmf.reqif10.EnumValue;
import org.polarsys.capella.vp.requirements.importer.transposer.bridge.ReqIFMapping;
import org.polarsys.capella.vp.requirements.importer.transposer.bridge.ReqIFMappingQueries;
import org.polarsys.capella.vp.requirements.importer.transposer.bridge.RequirementEMFSYmbolFunction;
import org.polarsys.capella.vp.requirements.importer.transposer.bridge.TupleNP;
import org.polarsys.capella.vp.requirements.importer.transposer.bridge.query.TypeDefinitionQuery;
import org.polarsys.kitalpha.vp.requirements.Requirements.DataTypeDefinition;
import org.polarsys.kitalpha.vp.requirements.Requirements.RequirementsFactory;
import org.polarsys.kitalpha.vp.requirements.Requirements.TypesFolder;

public class TypeDefinitionRule extends AbstractRule<DatatypeDefinition, TupleNP<Object>> {

  public TypeDefinitionRule(ReqIFMapping mapping, TypeDefinitionQuery provider) {
    super(mapping, provider, "TypeDefinition");
  }

  public TupleNP<Object> createTarget(DatatypeDefinition source, IQueryExecution queryExecution) {
    Map<String, Object> createdElements = new HashMap<String, Object>();

    if (source instanceof DatatypeDefinitionEnumeration) {
      DatatypeDefinitionEnumeration srcAttEnumDef = (DatatypeDefinitionEnumeration) source;
      org.polarsys.kitalpha.vp.requirements.Requirements.EnumerationDataTypeDefinition attEnumDef = RequirementsFactory.eINSTANCE
          .createEnumerationDataTypeDefinition();
      createdElements.put(RequirementEMFSYmbolFunction.getInstance().getEObjectSymbol(srcAttEnumDef), attEnumDef);

      attEnumDef.setReqIFIdentifier(srcAttEnumDef.getIdentifier());
      attEnumDef.setReqIFLongName(srcAttEnumDef.getLongName());
      
      for (EnumValue srcEnumValue : srcAttEnumDef.getSpecifiedValues())
      {
        org.polarsys.kitalpha.vp.requirements.Requirements.EnumValue enumValue = RequirementsFactory.eINSTANCE.createEnumValue();
        createdElements.put(RequirementEMFSYmbolFunction.getInstance().getEObjectSymbol(srcEnumValue), enumValue);
        enumValue.setReqIFIdentifier(srcEnumValue.getIdentifier());
        enumValue.setReqIFLongName(srcEnumValue.getLongName());
        attEnumDef.getSpecifiedValues().add(enumValue);
      }
      return new TupleNP<Object>(attEnumDef, createdElements);
    }
    
    DataTypeDefinition type = RequirementsFactory.eINSTANCE.createDataTypeDefinition();
    createdElements.put(RequirementEMFSYmbolFunction.getInstance().getEObjectSymbol(source), type);

    type.setReqIFIdentifier(source.getIdentifier());
    type.setReqIFLongName(source.getLongName());
    return new TupleNP<Object>(type, createdElements);
  }

  public void defineTarget(DatatypeDefinition datatypedefinition, TupleNP<Object> target, IQueryExecution queryEnv,
      IMappingExecution ruleEnv) {
    Object typeInTargetModel = ruleEnv.getOne(datatypedefinition, ITuple.class);
    if (typeInTargetModel instanceof TupleNP<?>) {
      typeInTargetModel = ((TupleNP<?>) typeInTargetModel).getRoot();
    }
    if (typeInTargetModel instanceof DataTypeDefinition) {
      TypesFolder folder = ReqIFMappingQueries.getTypesFolder(getMapping().getContext(),
          getMapping().getTemporaryScope());
      if (folder != null) {
        folder.getOwnedDefinitionTypes().add((DataTypeDefinition) typeInTargetModel);
      }
    }
  }
}
