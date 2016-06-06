package ec.com.tnb.mibus.injection.context;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by f3r10 on 30/5/16.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentContext {
}
