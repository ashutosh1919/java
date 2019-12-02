/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.core;

import java.util.List;
import org.tensorflow.DataType;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.nio.nd.Shape;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.types.TInt64;

/**
 * Creates a dataset that uses a custom thread pool to compute `input_dataset`.
 */
public final class ExperimentalPrivateThreadPoolDataset extends PrimitiveOp implements Operand<Object> {
  
  /**
   * Factory method to create a class wrapping a new ExperimentalPrivateThreadPoolDataset operation.
   * 
   * @param scope current scope
   * @param inputDataset 
   * @param numThreads Identifies the number of threads to use for the private threadpool.
   * @param outputTypes 
   * @param outputShapes 
   * @return a new instance of ExperimentalPrivateThreadPoolDataset
   */
  public static ExperimentalPrivateThreadPoolDataset create(Scope scope, Operand<?> inputDataset, Operand<TInt64> numThreads, List<DataType<?>> outputTypes, List<Shape> outputShapes) {
    OperationBuilder opBuilder = scope.env().opBuilder("ExperimentalPrivateThreadPoolDataset", scope.makeOpName("ExperimentalPrivateThreadPoolDataset"));
    opBuilder.addInput(inputDataset.asOutput());
    opBuilder.addInput(numThreads.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    DataType[] outputTypesArray = new DataType[outputTypes.size()];
    for (int i = 0; i < outputTypesArray.length; ++i) {
      outputTypesArray[i] = outputTypes.get(i);
    }
    opBuilder.setAttr("output_types", outputTypesArray);
    Shape[] outputShapesArray = new Shape[outputShapes.size()];
    for (int i = 0; i < outputShapesArray.length; ++i) {
      outputShapesArray[i] = outputShapes.get(i);
    }
    opBuilder.setAttr("output_shapes", outputShapesArray);
    return new ExperimentalPrivateThreadPoolDataset(opBuilder.build());
  }
  
  /**
   */
  public Output<?> handle() {
    return handle;
  }
  
  @Override
  @SuppressWarnings("unchecked")
  public Output<Object> asOutput() {
    return (Output<Object>) handle;
  }
  
  private Output<?> handle;
  
  private ExperimentalPrivateThreadPoolDataset(Operation operation) {
    super(operation);
    int outputIdx = 0;
    handle = operation.output(outputIdx++);
  }
}