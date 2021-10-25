package com.example.snapchatcopy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;

public class CameraFragment extends Fragment {
    PreviewView previewView;
    private ProcessCameraProvider cameraProvider;
    CameraSelector cameraSelector;
    private Camera camera;
    private ImageCapture imageCapture;
    private View circleIcon;

    @Override
    public void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        previewView = view.findViewById(R.id.camera_preview_view);

        circleIcon = view.findViewById(R.id.circle_icon);
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext());
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    cameraProvider = cameraProviderFuture.get();
                    bindPreview();


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(requireContext()));
        circleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageCapture.OutputFileOptions outputFileOptions =
                        new ImageCapture.OutputFileOptions.Builder(new File(getContext().getCacheDir() + "something")).build();
                imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(requireContext()),
                        new ImageCapture.OnImageSavedCallback() {
                            @Override
                            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                                // insert your code here.
                                Uri uri = outputFileResults.getSavedUri();
                                goToSnapTakenFragment(uri);
                            }

                            @Override
                            public void onError(ImageCaptureException error) {
                                // insert your code here.
                            }
                        }
                );
            }
        });


    }

    private void goToSnapTakenFragment(Uri uri) {
        getChildFragmentManager().beginTransaction().replace(R.id.camera_view, SnapTakenFragment.newInstance(uri)).addToBackStack(null).commit();
    }

    private void bindPreview() {
        cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        Preview preview = new Preview.Builder().build();
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();
        camera = cameraProvider.bindToLifecycle((LifecycleOwner) (this), cameraSelector, imageCapture, preview);

        preview.setSurfaceProvider(previewView.getSurfaceProvider());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.camera_fragment, container, false);
    }
}
