package com.appboy.sample;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.braze.IBrazeEndpointProvider;

/**
 * An {@link IBrazeEndpointProvider} that sets an override endpoint if given.
 */
public class DroidboyEndpointProvider implements IBrazeEndpointProvider {
  private final Uri mEndpointUri;
  private final String mEndpoint;

  public DroidboyEndpointProvider(@NonNull String endpoint) {
    mEndpoint = endpoint;
    mEndpointUri = Uri.parse(endpoint);
  }

  public Uri getApiEndpoint(Uri brazeEndpoint) {
    final Uri.Builder builder = brazeEndpoint.buildUpon();

    if (mEndpointUri.getScheme().equals("http")) {
      builder.scheme("http");
    }

    if (mEndpointUri.getEncodedAuthority() != null) {
      builder.encodedAuthority(mEndpointUri.getEncodedAuthority());
    } else {
      builder.encodedAuthority(mEndpoint);
    }
    return builder.build();
  }
}
