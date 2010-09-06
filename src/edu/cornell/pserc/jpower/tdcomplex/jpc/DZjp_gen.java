/*
 * Copyright (C) 2010 Richard Lincoln
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 */

package edu.cornell.pserc.jpower.tdcomplex.jpc;

import cern.colt.matrix.tdouble.DoubleMatrix1D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.matrix.tint.IntMatrix1D;
import edu.cornell.pserc.jpower.tdcomplex.DZjp_idx;

public class DZjp_gen extends DZjp_idx {

	/** bus number */
	public IntMatrix1D gen_bus;

	/** Pg, real power output (MW) */
	public DoubleMatrix1D Pg;

	/** Qg, reactive power output (MVAr) */
	public DoubleMatrix1D Qg;

	/** Qmax, maximum reactive power output at Pmin (MVAr) */
	public DoubleMatrix1D Qmax;

	/** Qmin, minimum reactive power output at Pmin (MVAr) */
	public DoubleMatrix1D Qmin;

	/** Vg, voltage magnitude setpoprotected static final int (p.u.) */
	public DoubleMatrix1D Vg;

	/** mBase, total MVA base of this machine, defaults to baseMVA */
	public DoubleMatrix1D mBase;

	/** status, 1 - machine in service, 0 - machine out of service */
	public IntMatrix1D gen_status;

	/** Pmax, maximum real power output (MW) */
	public DoubleMatrix1D Pmax;

	/** Pmin, minimum real power output (MW) */
	public DoubleMatrix1D Pmin;

	/** Pc1, lower real power output of PQ capability curve (MW) */
	public DoubleMatrix1D Pc1;

	/** Pc2, upper real power output of PQ capability curve (MW) */
	public DoubleMatrix1D Pc2;

	/** Qc1min, minimum reactive power output at Pc1 (MVAr) */
	public DoubleMatrix1D Qc1min;

	/** Qc1max, maximum reactive power output at Pc1 (MVAr) */
	public DoubleMatrix1D Qc1max;

	/** Qc2min, minimum reactive power output at Pc2 (MVAr) */
	public DoubleMatrix1D Qc2min;

	/** Qc2max, maximum reactive power output at Pc2 (MVAr) */
	public DoubleMatrix1D Qc2max;

	/** ramp rate for load following/AGC (MW/min) */
	public DoubleMatrix1D ramp_agc;

	/** ramp rate for 10 minute reserves (MW) */
	public DoubleMatrix1D ramp_10;

	/** ramp rate for 30 minute reserves (MW) */
	public DoubleMatrix1D ramp_30;

	/** ramp rate for reactive power (2 sec timescale) (MVAr/min) */
	public DoubleMatrix1D ramp_q;

	/** area participation factor */
	public DoubleMatrix1D apf;

	/*
	 * included in opf solution, not necessarily in input
	 * assume objective function has units, u
	 *
	 */

	/** Kuhn-Tucker multiplier on upper Pg limit (u/MW) */
	protected DoubleMatrix1D mu_Pmax;

	/** Kuhn-Tucker multiplier on lower Pg limit (u/MW) */
	protected DoubleMatrix1D mu_Pmin;

	/** Kuhn-Tucker multiplier on upper Qg limit (u/MVAr) */
	protected DoubleMatrix1D mu_Qmax;

	/** Kuhn-Tucker multiplier on lower Qg limit (u/MVAr) */
	protected DoubleMatrix1D mu_Qmin;

	/**
	 *
	 * @return the number of generators.
	 */
	public int size() {
		return (int) this.gen_bus.size();
	}

	/**
	 *
	 * @return a full copy of the generator data.
	 */
	public DZjp_gen copy() {
		return copy(null);
	}

	/**
	 *
	 * @return a copy of the generator data for the given indexes.
	 */
	public DZjp_gen copy(int[] indexes) {
		DZjp_gen other = new DZjp_gen();

		other.gen_bus = this.gen_bus.viewSelection(indexes).copy();
		other.Pg = this.Pg.viewSelection(indexes).copy();
		other.Qg = this.Qg.viewSelection(indexes).copy();
		other.Qmax = this.Qmax.viewSelection(indexes).copy();
		other.Qmin = this.Qmin.viewSelection(indexes).copy();
		other.Vg = this.Vg.viewSelection(indexes).copy();
		other.mBase = this.mBase.viewSelection(indexes).copy();
		other.gen_status = this.gen_status.viewSelection(indexes).copy();
		other.Pmax = this.Pmax.viewSelection(indexes).copy();
		other.Pmin = this.Pmin.viewSelection(indexes).copy();
		other.Pc1 = this.Pc1.viewSelection(indexes).copy();
		other.Pc2 = this.Pc2.viewSelection(indexes).copy();
		other.Qc1min = this.Qc1min.viewSelection(indexes).copy();
		other.Qc1max = this.Qc1max.viewSelection(indexes).copy();
		other.Qc2min = this.Qc2min.viewSelection(indexes).copy();
		other.Qc2max = this.Qc2max.viewSelection(indexes).copy();
		other.ramp_agc = this.ramp_agc.viewSelection(indexes).copy();
		other.ramp_10 = this.ramp_10.viewSelection(indexes).copy();
		other.ramp_30 = this.ramp_30.viewSelection(indexes).copy();
		other.ramp_q = this.ramp_q.viewSelection(indexes).copy();
		other.apf = this.apf.viewSelection(indexes).copy();

		other.mu_Pmax = this.mu_Pmax.viewSelection(indexes).copy();
		other.mu_Pmin = this.mu_Pmin.viewSelection(indexes).copy();
		other.mu_Qmax = this.mu_Qmax.viewSelection(indexes).copy();
		other.mu_Qmin = this.mu_Qmin.viewSelection(indexes).copy();

		return other;
	}

	/**
	 * Updates the generator data for the given indexes.
	 *
	 * @param other generator data source
	 * @param indexes generator indexes
	 */
	public void update(DZjp_gen other, int[] indexes) {

		this.gen_bus.viewSelection(indexes).assign(other.gen_bus.viewSelection(indexes));
		this.Pg.viewSelection(indexes).assign(other.Pg.viewSelection(indexes));
		this.Qg.viewSelection(indexes).assign(other.Qg.viewSelection(indexes));
		this.Qmax.viewSelection(indexes).assign(other.Qmax.viewSelection(indexes));
		this.Qmin.viewSelection(indexes).assign(other.Qmin.viewSelection(indexes));
		this.Vg.viewSelection(indexes).assign(other.Vg.viewSelection(indexes));
		this.mBase.viewSelection(indexes).assign(other.mBase.viewSelection(indexes));
		this.gen_status.viewSelection(indexes).assign(other.gen_status.viewSelection(indexes));
		this.Pmax.viewSelection(indexes).assign(other.Pmax.viewSelection(indexes));
		this.Pmin.viewSelection(indexes).assign(other.Pmin.viewSelection(indexes));
		this.Pc1.viewSelection(indexes).assign(other.Pc1.viewSelection(indexes));
		this.Pc2.viewSelection(indexes).assign(other.Pc2.viewSelection(indexes));
		this.Qc1min.viewSelection(indexes).assign(other.Qc1min.viewSelection(indexes));
		this.Qc1max.viewSelection(indexes).assign(other.Qc1max.viewSelection(indexes));
		this.Qc2min.viewSelection(indexes).assign(other.Qc2min.viewSelection(indexes));
		this.Qc2max.viewSelection(indexes).assign(other.Qc2max.viewSelection(indexes));
		this.ramp_agc.viewSelection(indexes).assign(other.ramp_agc.viewSelection(indexes));
		this.ramp_10.viewSelection(indexes).assign(other.ramp_10.viewSelection(indexes));
		this.ramp_30.viewSelection(indexes).assign(other.ramp_30.viewSelection(indexes));
		this.ramp_q.viewSelection(indexes).assign(other.ramp_q.viewSelection(indexes));
		this.apf.viewSelection(indexes).assign(other.apf.viewSelection(indexes));

		this.mu_Pmax.viewSelection(indexes).assign(other.mu_Pmax.viewSelection(indexes));
		this.mu_Pmin.viewSelection(indexes).assign(other.mu_Pmin.viewSelection(indexes));
		this.mu_Qmax.viewSelection(indexes).assign(other.mu_Qmax.viewSelection(indexes));
		this.mu_Qmin.viewSelection(indexes).assign(other.mu_Qmin.viewSelection(indexes));
	}

	/**
	 *
	 * @param other
	 */
	public void update(DoubleMatrix2D other) {
		update(other, null);
	}

	/**
	 *
	 * @param other
	 * @param indexes
	 */
	public void update(DoubleMatrix2D other, int[] indexes) {

		this.gen_bus.viewSelection(indexes).assign( intm(other.viewColumn(GEN_BUS).viewSelection(indexes)) );
		this.Pg.viewSelection(indexes).assign(other.viewColumn(PG).viewSelection(indexes));
		this.Qg.viewSelection(indexes).assign(other.viewColumn(QG).viewSelection(indexes));
		this.Qmax.viewSelection(indexes).assign(other.viewColumn(QMAX).viewSelection(indexes));
		this.Qmin.viewSelection(indexes).assign(other.viewColumn(QMIN).viewSelection(indexes));
		this.Vg.viewSelection(indexes).assign(other.viewColumn(VG).viewSelection(indexes));
		this.mBase.viewSelection(indexes).assign(other.viewColumn(MBASE).viewSelection(indexes));
		this.gen_status.viewSelection(indexes).assign( intm(other.viewColumn(GEN_STATUS).viewSelection(indexes)) );
		this.Pmax.viewSelection(indexes).assign(other.viewColumn(PMAX).viewSelection(indexes));
		this.Pmin.viewSelection(indexes).assign(other.viewColumn(PMIN).viewSelection(indexes));
		this.Pc1.viewSelection(indexes).assign(other.viewColumn(PC1).viewSelection(indexes));
		this.Pc2.viewSelection(indexes).assign(other.viewColumn(PC2).viewSelection(indexes));
		this.Qc1min.viewSelection(indexes).assign(other.viewColumn(QC1MIN).viewSelection(indexes));
		this.Qc1max.viewSelection(indexes).assign(other.viewColumn(QC1MAX).viewSelection(indexes));
		this.Qc2min.viewSelection(indexes).assign(other.viewColumn(QC2MIN).viewSelection(indexes));
		this.Qc2max.viewSelection(indexes).assign(other.viewColumn(QC2MAX).viewSelection(indexes));
		this.ramp_agc.viewSelection(indexes).assign(other.viewColumn(RAMP_AGC).viewSelection(indexes));
		this.ramp_10.viewSelection(indexes).assign(other.viewColumn(RAMP_10).viewSelection(indexes));
		this.ramp_30.viewSelection(indexes).assign(other.viewColumn(RAMP_30).viewSelection(indexes));
		this.ramp_q.viewSelection(indexes).assign(other.viewColumn(RAMP_Q).viewSelection(indexes));
		this.apf.viewSelection(indexes).assign(other.viewColumn(APF).viewSelection(indexes));

		if (other.columns() > APF) {
			this.mu_Pmax.viewSelection(indexes).assign(other.viewColumn(MU_PMAX).viewSelection(indexes));
			this.mu_Pmin.viewSelection(indexes).assign(other.viewColumn(MU_PMIN).viewSelection(indexes));
			this.mu_Qmax.viewSelection(indexes).assign(other.viewColumn(MU_QMAX).viewSelection(indexes));
			this.mu_Qmin.viewSelection(indexes).assign(other.viewColumn(MU_QMIN).viewSelection(indexes));
		}
	}

}
