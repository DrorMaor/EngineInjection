package com.maorlamp.engineinjection

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the theme based on the system's default night mode
        val nightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (nightMode) {
            Configuration.UI_MODE_NIGHT_YES -> delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Configuration.UI_MODE_NIGHT_NO -> delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        setContentView(R.layout.activity_main)

        val circleSquareDiameter = 0.7854
        val cmPow3 = 0.061

        // DISPLACEMENT CALC
        val tvDisplacementCalc = findViewById<TextView>(R.id.tvDisplacementCalc)
        val lvDisplacementCalc = findViewById<LinearLayout>(R.id.lvDisplacementCalc)

        // Add click listeners to headers
        tvDisplacementCalc.setOnClickListener { toggleContentVisibility(lvDisplacementCalc) }

        // Hide content initially
        lvDisplacementCalc.visibility = View.GONE

        val btnCID = findViewById<Button>(R.id.btnCID)
        btnCID.setOnClickListener {
            val etBore: EditText = findViewById(R.id.etBore)
            val etStroke: EditText = findViewById(R.id.etStroke)
            val etCylinders: EditText = findViewById(R.id.etCylinders)
            val bore: Double = etBore.text.toString().toDouble()
            val stroke: Double = etStroke.text.toString().toDouble()
            val cylinders: Double = etCylinders.text.toString().toDouble()
            val CID: Double = bore.pow(2) * stroke * circleSquareDiameter * cylinders
            val tvCID: TextView = findViewById(R.id.tvCID)
            tvCID.text = CID.toString()
        }
        //////////////////////////////

        // DISPLACEMENT CALC
        val tvInjectorCalcFlowRate = findViewById<TextView>(R.id.tvInjectorCalcFlowRate)
        val lvInjectorCalcFlowRate = findViewById<LinearLayout>(R.id.lvInjectorCalcFlowRate)

        // Add click listeners to headers
        tvInjectorCalcFlowRate.setOnClickListener { toggleContentVisibility(lvInjectorCalcFlowRate) }

        // Hide content initially
        lvInjectorCalcFlowRate.visibility = View.GONE

        val btnNewFlowRate = findViewById<Button>(R.id.btnNewFlowRate)
        btnNewFlowRate.setOnClickListener {
            val etOldPSI: EditText = findViewById(R.id.etOldPSI)
            val etNewPSI: EditText = findViewById(R.id.etNewPSI)
            val etInjectorLbHr: EditText = findViewById(R.id.etInjectorLbHr)
            val oldPSI: Double = etOldPSI.text.toString().toDouble()
            val newPSI: Double = etNewPSI.text.toString().toDouble()
            val injectorLbHr: Double = etInjectorLbHr.text.toString().toDouble()
            val newFlowRate: Double = Math.sqrt(newPSI / oldPSI) * injectorLbHr
            val tvNewFlowRate: TextView = findViewById(R.id.tvNewFlowRate)
            tvNewFlowRate.text = newFlowRate.toString()
        }
        /////////////////


        // INJECTOR TIME CALC
        val tvInjectorTimeCalc = findViewById<TextView>(R.id.tvInjectorTimeCalc)
        val lvInjectorTimeCalc = findViewById<LinearLayout>(R.id.lvInjectorTimeCalc)

        // Add click listeners to headers
        tvInjectorTimeCalc.setOnClickListener { toggleContentVisibility(lvInjectorTimeCalc) }

        // Hide content initially
        lvInjectorTimeCalc.visibility = View.GONE

        val btnInjectorTime = findViewById<Button>(R.id.btnInjectorTime)
        btnInjectorTime.setOnClickListener {
            val etRPM: EditText = findViewById(R.id.etRPM)
            val rpm: Int = etRPM.text.toString().toInt()

            val cyclesSec: Double = (rpm.toDouble() / 2.0) / 60.0
            val tvCyclesSec: TextView = findViewById(R.id.tvCyclesSec)
            tvCyclesSec.text = cyclesSec.toString()

            val totalSecAvail: Double = 1.0 / cyclesSec
            val tvTotalSecAvail: TextView = findViewById(R.id.tvTotalSecAvail)
            tvTotalSecAvail.text = totalSecAvail.toString()

            val secAvail80: Double = totalSecAvail * 0.8
            val tvSecAvail80: TextView = findViewById(R.id.tvSecAvail80)
            tvSecAvail80.text = secAvail80.toString()
        }
        /////////////////


        // COMPRESSION RATIO CALC
        val tvCompressionRatioCalc = findViewById<TextView>(R.id.tvCompressionRatioCalc)
        val lvCompressionRatioCalc = findViewById<LinearLayout>(R.id.lvCompressionRatioCalc)

        // Add click listeners to headers
        tvCompressionRatioCalc.setOnClickListener { toggleContentVisibility(lvCompressionRatioCalc) }

        // Hide content initially
        lvCompressionRatioCalc.visibility = View.GONE

        val btnCalcCompressionRation = findViewById<Button>(R.id.btnCalcCompressionRation)
        btnCalcCompressionRation.setOnClickListener {
            val etCombustionChamberVolumeCC: EditText =
                findViewById(R.id.etCombustionChamberVolumeCC)
            val combustionChamberVolumeCC: Int = etCombustionChamberVolumeCC.text.toString().toInt()
            val etBore2: EditText = findViewById(R.id.etBore2)
            val bore2: Double = etBore2.text.toString().toDouble()
            val etStroke2: EditText = findViewById(R.id.etStroke2)
            val stroke2: Double = etStroke2.text.toString().toDouble()
            val etHeadGasketThickness: EditText = findViewById(R.id.etHeadGasketThickness)
            val headGasketThickness: Double = etHeadGasketThickness.text.toString().toDouble()
            val etHeadGasketBore: EditText = findViewById(R.id.etHeadGasketBore)
            val headGasketBore: Double = etHeadGasketBore.text.toString().toDouble()
            val etPistonToDeckClearance: EditText = findViewById(R.id.etPistonToDeckClearance)
            val pistonToDeckClearance: Double = etPistonToDeckClearance.text.toString().toDouble()
            val etPistonDomeDishVolumeCC: EditText = findViewById(R.id.etPistonDomeDishVolumeCC)
            val pistonDomeDishVolumeCC: Double = etPistonDomeDishVolumeCC.text.toString().toDouble()

            val chamberVolumeCI: Double = combustionChamberVolumeCC * cmPow3
            val tvChamberVolumeCI: TextView = findViewById(R.id.tvChamberVolumeCI)
            tvChamberVolumeCI.text = chamberVolumeCI.toString()

            val cylinderVolume: Double = bore2.pow(2) * stroke2 * circleSquareDiameter
            val tvCylinderVolume: TextView = findViewById(R.id.tvCylinderVolume)
            tvCylinderVolume.text = cylinderVolume.toString()

            val headGasketVolume: Double =
                headGasketBore.pow(2) * headGasketThickness * circleSquareDiameter
            val tvHeadGasketVolume: TextView = findViewById(R.id.tvHeadGasketVolume)
            tvHeadGasketVolume.text = headGasketVolume.toString()

            val PtoDCVolumeCI: Double = bore2.pow(2) * pistonToDeckClearance * circleSquareDiameter
            val tvPtoDCVolumeCI: TextView = findViewById(R.id.tvPtoDCVolumeCI)
            tvPtoDCVolumeCI.text = PtoDCVolumeCI.toString()

            val pistonDomeDishVolumeCI: Double = pistonDomeDishVolumeCC * cmPow3
            val tvPistonDomeDishVolumeCI: TextView = findViewById(R.id.tvPistonDomeDishVolumeCI)
            tvPistonDomeDishVolumeCI.text = pistonDomeDishVolumeCI.toString()

            val volumeAtBDC: Double =
                chamberVolumeCI + headGasketVolume + PtoDCVolumeCI + cylinderVolume - pistonDomeDishVolumeCI
            val tvVolumeAtBDC: TextView = findViewById(R.id.tvVolumeAtBDC)
            tvVolumeAtBDC.text = volumeAtBDC.toString()

            val volumeAtTDC: Double =
                chamberVolumeCI + headGasketVolume + PtoDCVolumeCI - pistonDomeDishVolumeCI
            val tvVolumeAtTDC: TextView = findViewById(R.id.tvVolumeAtTDC)
            tvVolumeAtTDC.text = volumeAtTDC.toString()

            val compressionRatio: Double = volumeAtBDC / volumeAtTDC
            val tvCompressionRatio: TextView = findViewById(R.id.tvCompressionRatio)
            tvCompressionRatio.text = compressionRatio.toString()

        }
        /////////////////


        // INJECTOR PUMPING VOLUME
        val tvInjectorPumpingVolume = findViewById<TextView>(R.id.tvInjectorPumpingVolume)
        val lvInjectorPumpingVolume = findViewById<LinearLayout>(R.id.lvInjectorPumpingVolume)

        // Add click listeners to headers
        tvInjectorPumpingVolume.setOnClickListener { toggleContentVisibility(lvInjectorPumpingVolume) }

        // Hide content initially
        lvInjectorPumpingVolume.visibility = View.GONE

        val btnInjectorPumpingVolume = findViewById<Button>(R.id.btnInjectorPumpingVolume)
        btnInjectorPumpingVolume.setOnClickListener {
            val etCID: EditText = findViewById(R.id.etCID)
            val CID: Int = etCID.text.toString().toInt()
            val etRPM1: EditText = findViewById(R.id.etRPM1)
            val RPM1: Int = etRPM1.text.toString().toInt()
            val CFM: Double = ((CID * RPM1) / (1728 * 2)).toDouble()
            val tvCFM: TextView = findViewById(R.id.tvCFM)
            tvCFM.text = CFM.toString()
            val AIR_lb_hr: Double = CFM * 0.076
            val tvAIR_lb_hr: TextView = findViewById(R.id.tvAIR_lb_hr)
            tvAIR_lb_hr.text = AIR_lb_hr.toString()

            val etNumberOfCylinders: EditText = findViewById(R.id.etNumberOfCylinders)
            val numberOfCylinders: Int = etNumberOfCylinders.text.toString().toInt()
            val etAFR: EditText = findViewById(R.id.etAFR)
            val AFR: Double = etAFR.text.toString().toDouble()
            val neededFlowInjection_lb_min: Double = (AIR_lb_hr / numberOfCylinders) / AFR
            val tvNeededFlowInjection_lb_min: TextView =
                findViewById(R.id.tvNeededFlowInjection_lb_min)
            tvNeededFlowInjection_lb_min.text = neededFlowInjection_lb_min.toString()

            val etInjectorLbHr1: EditText = findViewById(R.id.etInjectorLbHr1)
            val injectorLbHr1: Int = etInjectorLbHr1.text.toString().toInt()
            val injectorFlowCapacity: Double = injectorLbHr1 / 60.0
            val tvInjectorFlowCapacity: TextView = findViewById(R.id.tvInjectorFlowCapacity)
            tvInjectorFlowCapacity.text = injectorFlowCapacity.toString()

            val tvDC_pctNeeded: TextView = findViewById(R.id.tvDC_pctNeeded)
            val DC_pctNeeded: Double = neededFlowInjection_lb_min / injectorFlowCapacity
            tvDC_pctNeeded.text = DC_pctNeeded.toString()

            val tvDC_pctMoreThan80: TextView = findViewById(R.id.tvDC_pctMoreThan80)
            val DC_pctMoreThan80: Double = (DC_pctNeeded * injectorLbHr1) / 0.8
            tvDC_pctMoreThan80.text = DC_pctMoreThan80.toString()

        }
        //////////////////////////////

    }

    private fun toggleContentVisibility(content: View) {
        if (content.visibility == View.VISIBLE)
            content.visibility = View.GONE
        else
            content.visibility = View.VISIBLE
    }
}



