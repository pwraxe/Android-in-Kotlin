private void designDrawer() {

        binding.idDrawerLayout.setScrimColor(Color.TRANSPARENT);
        binding.idDrawerLayout.setDrawerElevation(0.0f);

        binding.idDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

                float slideX = drawerView.getWidth() * slideOffset;
                binding.idDrawerBottomContainer.setTranslationX(slideX);
                binding.idDrawerBottomContainer.setScaleX(1 - slideOffset / 8f );
                binding.idDrawerBottomContainer.setScaleY(1 - slideOffset / 12f);

                int so = (int) slideOffset;

                if(so == 1){
                    binding.idDrawerBottomContainer.setRadius(72.0f);
                }else{
                    binding.idDrawerBottomContainer.setRadius(0.0f);
                }
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) { }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) { }

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }
