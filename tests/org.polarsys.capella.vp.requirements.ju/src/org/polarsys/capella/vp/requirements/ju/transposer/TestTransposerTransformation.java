/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.vp.requirements.ju.transposer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.polarsys.capella.vp.requirements.importer.transposer.activities.TransposerTransformation;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;

/**
 * @author Joao Barata
 */
public class TestTransposerTransformation extends TransposerTransformation {

  public static String getId() {
    return TestTransposerTransformation.class.getCanonicalName();
  }

  @Override
  protected IStatus _run(ActivityParameters activityParams) {
    // Override this to avoid launching the activity in a Job in testing mode
    IStatus status = mergeAndSave(activityParams, new NullProgressMonitor());
    return status;
  }
}
