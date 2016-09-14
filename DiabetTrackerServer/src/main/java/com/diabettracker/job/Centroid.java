package com.diabettracker.job;


class Centroid {
    private double mCx, mCy, mCz;
    private Cluster mCluster;

    public Centroid(double cx, double cy) {
        this.mCx = cx;
        this.mCy = cy;
        
    }

    public void calcCentroid() { 
        int numDP = mCluster.getNumDataPoints();
        double tempX = 0, tempY = 0, tempZ = 0;
        int i;
        //calculer le nouveau Centroid
        for (i = 0; i < numDP; i++) {
            tempX = tempX + mCluster.getDataPoint(i).getX(); 
            //total pour x
            tempY = tempY + mCluster.getDataPoint(i).getY(); 
            //total pour y
            tempZ = tempZ + mCluster.getDataPoint(i).getZ(); 
        }
        this.mCx = tempX / numDP;
        this.mCy = tempY / numDP;
        this.mCz = tempZ / numDP;
        //calcul le nouveau Distance Euclidean pour chaque Point
        tempX = 0;
        tempY = 0;
        tempZ = 0;
        for (i = 0; i < numDP; i++) {
            mCluster.getDataPoint(i).calcEuclideanDistance();
        }
        //calcul la somme des carrés pour le Cluster
        mCluster.calcSumOfSquares();
    }

    public void setCluster(Cluster c) {
        this.mCluster = c;
    }

    public double getCx() {
        return mCx;
    }

    public double getCy() {
        return mCy;
    }
    public double getCz() {
        return mCz;
    }

    public Cluster getCluster() {
        return mCluster;
    }

}

