package com.diabettracker.job;

import java.util.ArrayList;
import java.util.List;

public class JCA {
	private Cluster[] clusters;
	private int miter;
	private List<DataPoint> mDataPoints = new ArrayList<DataPoint>();
	private double mSWCSS;

	public JCA(int nbClusters, int iter, List<DataPoint> dataPoints) {
		clusters = new Cluster[nbClusters];
		for (int i = 0; i < nbClusters; i++) {
			clusters[i] = new Cluster("Cluster" + i);
		}
		this.miter = iter;
		this.mDataPoints = dataPoints;
	}

	private void calcSWCSS() {
		double temp = 0;
		for (int i = 0; i < clusters.length; i++) {
			temp = temp + clusters[i].getSumSqr();
		}
		mSWCSS = temp;
	}

	public void startAnalysis() {
		// les position de depart du centroid - etape 1
		setInitialCentroids();
		int n = 0;
		// attribut les points aux cluster
		loop1: while (true) {
			for (int l = 0; l < clusters.length; l++) {
				clusters[l].addDataPoint((DataPoint) mDataPoints.get(n));
				n++;
				if (n >= mDataPoints.size())
					break loop1;
			}
		}

		// calculer E pour tout les clusters
		calcSWCSS();

		// recalculer les centroids de cluster- etape 2
		for (int i = 0; i < clusters.length; i++) {
			clusters[i].getCentroid().calcCentroid();
		}

		// recalculer E pour tout les Clusters
		calcSWCSS();
		
		double tempEuDt;
		Cluster tempCluster;
		boolean matchFoundFlag;

		for (int i = 0; i < miter; i++) {
			// cluster 1
			for (int j = 0; j < clusters.length; j++) {
				for (int k = 0; k < clusters[j].getNumDataPoints(); k++) {

					// prendre le 1er element du 1er cluster
					// prendre l'Euclidean distance actuel
					tempEuDt = clusters[j].getDataPoint(k).getCurrentEuDt();
					tempCluster = null;
					matchFoundFlag = false;

					// appels au testEuclidean distance pour tout les clusters
					for (int l = 0; l < clusters.length; l++) {

						// si testEuclidean < currentEuclidean alors
						if (tempEuDt > clusters[j].getDataPoint(k).testEuclideanDistance(clusters[l].getCentroid())) {
							tempEuDt = clusters[j].getDataPoint(k).testEuclideanDistance(clusters[l].getCentroid());
							tempCluster = clusters[l];
							matchFoundFlag = true;
						}
						// if statement - Check whether the Last EuDt is >
						// Present EuDt

					}
					// for variable 'l' - Looping between different Clusters for
					// matching a Data Point.
					// add DataPoint to the cluster and calcSWCSS

					if (matchFoundFlag) {
						tempCluster.addDataPoint(clusters[j].getDataPoint(k));
						clusters[j].removeDataPoint(clusters[j].getDataPoint(k));
						for (int m = 0; m < clusters.length; m++) {
							clusters[m].getCentroid().calcCentroid();
						}

						// for variable 'm' - Recalculating centroids for all
						// Clusters

						calcSWCSS();
					}

					// if statement - A Data Point is eligible for transfer
					// between Clusters.
				}
				// for variable 'k' - Looping through all Data Points of the
				// current Cluster.
			} // for variable 'j' - Looping through all the Clusters.
		} // for variable 'i' - Number of iterations.
	}

	// Récupére les datapoints des différents clusters
	@SuppressWarnings("unchecked")
	public List<DataPoint>[] getClusterOutput() {
		List<DataPoint> v[] = new List[clusters.length];
		for (int i = 0; i < clusters.length; i++) {
			v[i] = clusters[i].getDataPoints();
		}
		return v;
	}

	private void setInitialCentroids() {
		// kn = (round((max-min)/k)*n)+min where n is from 0 to (k-1).
		double cx = 0, cy = 0;
		for (int n = 1; n <= clusters.length; n++) {
			cx = (((getMaxXValue() - getMinXValue()) / (clusters.length + 1)) * n) + getMinXValue();
			cy = (((getMaxYValue() - getMinYValue()) / (clusters.length + 1)) * n) + getMinYValue();
			Centroid c1 = new Centroid(cx, cy);
			clusters[n - 1].setCentroid(c1);
			c1.setCluster(clusters[n - 1]);
		}
	}

	private double getMaxXValue() {
		double temp;
		temp = ((DataPoint) mDataPoints.get(0)).getX();
		for (int i = 0; i < mDataPoints.size(); i++) {
			DataPoint dp = (DataPoint) mDataPoints.get(i);
			temp = (dp.getX() > temp) ? dp.getX() : temp;
		}
		return temp;
	}

	private double getMinXValue() {
		double temp = 0;
		temp = ((DataPoint) mDataPoints.get(0)).getX();
		for (int i = 0; i < mDataPoints.size(); i++) {
			DataPoint dp = (DataPoint) mDataPoints.get(i);
			temp = (dp.getX() < temp) ? dp.getX() : temp;
		}
		return temp;
	}

	private double getMaxYValue() {
		double temp = 0;
		temp = ((DataPoint) mDataPoints.get(0)).getY();
		for (int i = 0; i < mDataPoints.size(); i++) {
			DataPoint dp = (DataPoint) mDataPoints.get(i);
			temp = (dp.getY() > temp) ? dp.getY() : temp;
		}
		return temp;
	}

	private double getMinYValue() {
		double temp = 0;
		temp = ((DataPoint) mDataPoints.get(0)).getY();
		for (int i = 0; i < mDataPoints.size(); i++) {
			DataPoint dp = (DataPoint) mDataPoints.get(i);
			temp = (dp.getY() < temp) ? dp.getY() : temp;
		}
		return temp;
	}

	public int getKValue() {
		return clusters.length;
	}

	public int getIterations() {
		return miter;
	}

	public int getTotalDataPoints() {
		return mDataPoints.size();
	}

	public double getSWCSS() {
		return mSWCSS;
	}

	public Cluster getCluster(int pos) {
		return clusters[pos];
	}
	
	public Cluster[] getClusters() {
		return clusters;
	}
}
