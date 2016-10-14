/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.requirements.Requirements.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;
import org.polarsys.capella.vp.requirements.CapellaRequirements.CapellaOutgoingRelation;
import org.polarsys.capella.vp.requirements.model.edit.decorators.ItemProviderAdapterDecorator;
import org.polarsys.kitalpha.vp.requirements.Requirements.RelationType;

/**
 * @author Joao Barata
 */
public class CapellaOutgoingRelationItemProviderDecorator extends
		ItemProviderAdapterDecorator implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IItemPropertySource {

	public CapellaOutgoingRelationItemProviderDecorator(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
  public String getText(Object object) {
	  CapellaOutgoingRelation relation = (CapellaOutgoingRelation) object;

    RelationType type = relation.getRelationType();
    if (type != null) {
      return "[" + type.getReqIFLongName() + "] " + EObjectLabelProviderHelper.getText(relation.getTarget());
    }

    return super.getText(object);
  }
}