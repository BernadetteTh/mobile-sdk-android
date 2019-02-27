package com.jumio.sample.java;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.jumio.MobileSDK;
import com.jumio.sample.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Copyright 2019 Jumio Corporation All rights reserved.
 */
public class MainActivity extends AppCompatActivity {

	public static final String KEY_SWITCH_ONE_TEXT = "KEY_SWITCH_ONE_TEXT";
	public static final String KEY_SWITCH_TWO_TEXT = "KEY_SWITCH_TWO_TEXT";
	public static final String KEY_BUTTON_TEXT = "KEY_BUTTON_TEXT";
	public static final String KEY_API_TOKEN = "KEY_API_TOKEN";
	public static final String KEY_API_SECRET = "KEY_API_SECRET";

	/* PUT YOUR NETVERIFY API TOKEN AND SECRET HERE */
	private static String NETVERIFY_API_TOKEN = "";
	private static String NETVERIFY_API_SECRET = "";

	/* PUT YOUR BAM API TOKEN AND SECRET HERE */
	private static String BAM_API_TOKEN = "";
	private static String BAM_API_SECRET = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.

		SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(mViewPager);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * Check and request missing permissions for the SDK.
	 *
	 * @param requestCode the request code for the SDK
	 */
	public boolean checkPermissions(int requestCode) {
		if (!MobileSDK.hasAllRequiredPermissions(this)) {
			//Acquire missing permissions.
			String[] mp = MobileSDK.getMissingPermissions(this);

			ActivityCompat.requestPermissions(this, mp, requestCode);
			//The result is received in onRequestPermissionsResult.
			return false;
		} else {
			return true;
		}
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	private class SectionsPagerAdapter extends FragmentPagerAdapter {

		SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			Bundle bundle = new Bundle();

			switch (position) {
				case 0:
					NetverifyFragment nvFragment = new NetverifyFragment();
					bundle.putString(KEY_SWITCH_ONE_TEXT, getResources().getString(R.string.netverify_verification_enabled));
					bundle.putString(KEY_SWITCH_TWO_TEXT, getResources().getString(R.string.netverify_identity_verification_enabled));
					bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_netverify)));
					bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
					bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
					nvFragment.setArguments(bundle);
					return nvFragment;
                case 1:
                    NetverifyCustomFragment nvCustomFragment = new NetverifyCustomFragment();
                    bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_netverify_custom)));
                    bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
                    bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
                    nvCustomFragment.setArguments(bundle);
                    return nvCustomFragment;
				case 2:
					AuthenticationFragment authFragment = new AuthenticationFragment();
					bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_authentication)));
					bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
					bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
					authFragment.setArguments(bundle);
					return authFragment;
				case 3:
					AuthenticationCustomFragment authCustomFragment = new AuthenticationCustomFragment();
					bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_authentication)));
					bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
					bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
					authCustomFragment.setArguments(bundle);
					return authCustomFragment;
				case 4:
					DocumentVerificationFragment dvFragment = new DocumentVerificationFragment();
					bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_documentverification)));
					bundle.putString(KEY_SWITCH_ONE_TEXT, getResources().getString(R.string.documentverification_enable_extraction));
					bundle.putString(KEY_API_TOKEN, NETVERIFY_API_TOKEN);
					bundle.putString(KEY_API_SECRET, NETVERIFY_API_SECRET);
					dvFragment.setArguments(bundle);
					return dvFragment;
				case 5:
					BamFragment bamFragment = new BamFragment();
					bundle.putString(KEY_SWITCH_ONE_TEXT, getResources().getString(R.string.bam_expiry_required));
					bundle.putString(KEY_SWITCH_TWO_TEXT, getResources().getString(R.string.bam_cvv_required));
					bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_bamcheckout)));
					bundle.putString(KEY_API_TOKEN, BAM_API_TOKEN);
					bundle.putString(KEY_API_SECRET, BAM_API_SECRET);
					bamFragment.setArguments(bundle);
					return bamFragment;
				case 6:
					BamCustomFragment bamCustomFragment = new BamCustomFragment();
					bundle.putString(KEY_BUTTON_TEXT, String.format(getResources().getString(R.string.button_start), getResources().getString(R.string.section_bam_custom)));
					bundle.putString(KEY_API_TOKEN, BAM_API_TOKEN);
					bundle.putString(KEY_API_SECRET, BAM_API_SECRET);
					bamCustomFragment.setArguments(bundle);
					return bamCustomFragment;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 7;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0:
					return getResources().getString(R.string.section_netverify);
                case 1:
                    return getResources().getString(R.string.section_netverify_custom);
				case 2:
					return getResources().getString(R.string.section_authentication);
				case 3:
					return getResources().getString(R.string.section_authentication);
				case 4:
					return getResources().getString(R.string.section_documentverification);
				case 5:
					return getResources().getString(R.string.section_bamcheckout);
				case 6:
					return getResources().getString(R.string.section_bam_custom);
			}
			return null;
		}
	}
}
