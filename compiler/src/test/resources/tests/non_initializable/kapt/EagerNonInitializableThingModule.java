// Code generated by Deager. Do not edit.
package se.ansman.non_initializable;

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
    topLevelClass = NonInitializableThing.class
)
public abstract class EagerNonInitializableThingModule {
  private EagerNonInitializableThingModule() {
  }

  @Provides
  @IntoSet
  public static Initializable provideNonInitializableThingAsInitializable(
      Lazy<NonInitializableThing> nonInitializableThing) {
    return Initializable.fromLazy(nonInitializableThing);
  }
}