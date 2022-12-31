// Code generated by Deager. Do not edit.
package se.ansman.priority;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.multibindings.IntoSet;
import se.ansman.deager.Initializable;

@Module
@InstallIn(SingletonComponent.class)
@OriginatingElement(
    topLevelClass = Outer.class
)
public abstract class EagerOuterSomeThingModule {
  private EagerOuterSomeThingModule() {
  }

  @Provides
  @IntoSet
  public static Initializable provideSomeThingAsInitializable(Lazy<Outer.SomeThing> someThing) {
    return Initializable.fromLazy(someThing, /* priority */ 4711);
  }
}